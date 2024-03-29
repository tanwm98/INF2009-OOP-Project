package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Managers.ScreenManager;


public class FinishScreen implements Screen {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private MyGdxGame game;
    private int currentSelection = 0;
    private Music backgroundMusic;
    private Viewport viewport;
    private OrthographicCamera camera;


    public FinishScreen(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        backgroundImage = new Texture("Background/MenuScreen.png");
        camera = new OrthographicCamera();
        viewport = new FitViewport(1200,900, camera);
    }

    @Override
    public void show() {
    	if (backgroundMusic == null) {
        backgroundMusic=OutputManager.getInstance().musicStart(0);
    	}
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
        float gap = Gdx.graphics.getPpcY(); // Initialize Gap between lines
        float startY = centerY + lineHeight;
        GlyphLayout layout = new GlyphLayout(); //Calculating text width

        String finishText = "End of Demo, Thanks for playing!\n";
        layout.setText(OutputManager.getInstance().getFont(), finishText);
        float finishTextWidth = layout.width;
        float startX = centerX - finishTextWidth / 2; // To Center the text
        OutputManager.getInstance().draw(batch,finishText, startX, startY);
        startY -= lineHeight + gap;

        String menuText = "Back to Menu";
        layout.setText(OutputManager.getInstance().getFont(), menuText);
        float menuTextWidth = layout.width;
        startX = centerX - menuTextWidth / 2;
        OutputManager.getInstance().draw(batch,menuText, startX, startY,currentSelection == 0);
        startY -= lineHeight + gap;

        String exitText = "Exit";
        layout.setText(OutputManager.getInstance().getFont(), exitText);
        float exitWidth = layout.width;
        startX = centerX - exitWidth / 2;
        OutputManager.getInstance().draw(batch,exitText, startX, startY,currentSelection == 1);
        batch.end(); //

        mouseSelection();
        updateCurrentSelection();
    }

    private void mouseSelection() {
        Rectangle menurectangle = new Rectangle(650, 340, 305, 55);
        Rectangle exitrectangle = new Rectangle(750, 225, 95, 55);
        float textX = Gdx.input.getX();
        float textY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (menurectangle.contains(textX,textY)) {
            currentSelection=0;
        }
        if (exitrectangle.contains(textX,textY)) {
            currentSelection=1;
        }
        if (InputManager.getInstance().leftClick()) {
            if (menurectangle.contains(textX, textY)||exitrectangle.contains(textX, textY)) {
                selectOption();
            }
        }
    }

    private void updateCurrentSelection() {
        if (InputManager.getInstance().isUpKeyJustPressed()|| InputManager.getInstance().isWKeyJustPressed()) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = 1;
            }
        }
        if (InputManager.getInstance().isDownKeyJustPressed()|| InputManager.getInstance().isSKeyJustPressed()) {
            currentSelection++;
            
            if (currentSelection > 1) {
                currentSelection = 0;
            }
        }
        if (InputManager.getInstance().isEnterKeyJustPressed()) {
            selectOption();
        }
    }

    private void selectOption() {
        switch (currentSelection) {
            case 0:
                ScreenManager.getInstance(game).pushScreen(new MainMenuScreen(game));
                break;
            case 1:
                Gdx.app.exit();
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

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
        if (batch != null) {
            batch.dispose();
        }

        if (backgroundImage != null) {
            backgroundImage.dispose();
        }
        backgroundMusic.dispose();
    }
}
