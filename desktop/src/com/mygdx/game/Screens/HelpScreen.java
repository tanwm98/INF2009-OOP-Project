package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Managers.ScreenManager;
import com.mygdx.game.MyGdxGame;


public class HelpScreen implements Screen {

        private SpriteBatch batch;
        private Texture backgroundImage;
        private MyGdxGame game;
        private ScreenManager screenmanager;
        private int currentSelection = 0;
        private Music backgroundMusic;
        private Viewport viewport;
        private OrthographicCamera camera;

        private String aboutText = "Navigate a spaceship through our solar system\n exploring eight planets while avoiding satellites.\n" +
                "\nHitting a satellite decreases health, but colliding \nwith asteroids triggers mini-games that can boost \nyour score.";
        private String helpText = "\n\n\nUse arrow keys or WASD keys to move spaceship.";
        private String exitText = "\nPress the Escape key to return to the main menu.";
        public HelpScreen(MyGdxGame game) {
                this.game = game;
                batch = new SpriteBatch();
                backgroundImage = new Texture("Background/MenuScreen.png");
                screenmanager = new ScreenManager(game);
                camera = new OrthographicCamera();
                viewport = new FitViewport(1200,900,camera);
        }

        @Override
        public void show() {
        	if (backgroundMusic == null) {
        	backgroundMusic=OutputManager.getInstance().musicStart(0);
        	}
                Gdx.input.setInputProcessor(new InputAdapter() {
					@Override
                    public boolean keyDown(int keycode) {
                        if (keycode == Input.Keys.ESCAPE) {
                        	screenmanager.pushScreen(new MainMenuScreen(game));
                            return true;
                        }
                        return false; 
                    }
                });
        }

        @Override
        public void render(float delta) {
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                viewport.apply();
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                OutputManager.getInstance().draw(batch,backgroundImage,0,0,viewport.getWorldWidth(), viewport.getWorldHeight());

                // Calculate the center of the screen
                float centerX = viewport.getWorldWidth() / 2f;
                float centerY = viewport.getWorldHeight() / 2f;
                float lineHeight = OutputManager.getInstance().getFont().getLineHeight(); // Get the height of the font
                float gap = 4 * Gdx.graphics.getPpcY(); // Initialize Gap between lines
                float startY = centerY +gap;
                GlyphLayout layout = new GlyphLayout(); //Calculating text width

                // Set the text with wrapping and center alignment
                layout.setText(OutputManager.getInstance().getFont(), aboutText);
                float textWidth = layout.width;
                float startX = centerX - textWidth / 2; // To Center the text
                OutputManager.getInstance().draw(batch,aboutText, startX, startY);
                startY -= lineHeight + gap;

                layout.setText(OutputManager.getInstance().getFont(), helpText);
                float settingsWidth = layout.width;
                startX = centerX - settingsWidth / 2;
                OutputManager.getInstance().draw(batch,helpText, startX, startY);
                startY -= lineHeight + gap;

                layout.setText(OutputManager.getInstance().getFont(), exitText);
                float aboutWidth = layout.width;
                startX = centerX - aboutWidth / 2;
                OutputManager.getInstance().draw(batch,exitText, startX, startY);
                batch.end(); 

        }
        @Override
        public void resize(int width, int height) {
                viewport.update(width, height);
                camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
                camera.update();
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void hide() {
        	OutputManager.getInstance().soundEnd(backgroundMusic);
        }
        @Override
        public void dispose() {
                if (backgroundImage != null) {
                        backgroundImage.dispose();
                }
                if (backgroundMusic != null)
                {
                        backgroundMusic.dispose();
                }
                batch.dispose();
        }
}
