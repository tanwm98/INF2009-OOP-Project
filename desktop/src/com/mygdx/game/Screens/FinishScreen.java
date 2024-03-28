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
import com.mygdx.game.Managers.ScreenManager;

public class FinishScreen implements Screen {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private MyGdxGame game;
    private ScreenManager screenmanager;
    private int currentSelection = 0;
    private Music backgroundMusic;
    private Viewport viewport;
    private OrthographicCamera camera;


    public FinishScreen(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        backgroundImage = new Texture("Background/MenuScreen.png");
        screenmanager = new ScreenManager(game);
        camera = new OrthographicCamera();
        viewport = new FitViewport(1200,900, camera);
    }

    @Override
    public void show() {
        backgroundMusic=screenmanager.getoutputManager().musicStart(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        screenmanager.getoutputManager().draw(batch,backgroundImage,0,0,viewport.getWorldWidth(), viewport.getWorldHeight());
        // Calculate the center of the screen
        float centerX = viewport.getWorldWidth() / 2f;
        float centerY = viewport.getWorldHeight() / 2f;
        float lineHeight = screenmanager.getoutputManager().getFont().getLineHeight(); // Get the height of the font
        float gap = Gdx.graphics.getPpcY(); // Initialize Gap between lines
        float startY = centerY + lineHeight;
        GlyphLayout layout = new GlyphLayout(); //Calculating text width

        String finishText = "End of Demo, Thanks for playing!\n";
        layout.setText(screenmanager.getoutputManager().getFont(), finishText);
        float finishTextWidth = layout.width;
        float startX = centerX - finishTextWidth / 2; // To Center the text
        screenmanager.getoutputManager().draw(batch,finishText, startX, startY);
        startY -= lineHeight + gap;

        String menuText = "Back to Menu";
        layout.setText(screenmanager.getoutputManager().getFont(), menuText);
        float menuTextWidth = layout.width;
        startX = centerX - menuTextWidth / 2;
        screenmanager.getoutputManager().draw(batch,menuText, startX, startY,currentSelection == 0);
        startY -= lineHeight + gap;

        String exitText = "Exit";
        layout.setText(screenmanager.getoutputManager().getFont(), exitText);
        float exitWidth = layout.width;
        startX = centerX - exitWidth / 2;
        screenmanager.getoutputManager().draw(batch,exitText, startX, startY,currentSelection == 1);
        batch.end(); //

        mouseSelection();
        updateCurrentSelection();
    }

    private void mouseSelection() {
        Rectangle startrectangle = new Rectangle(665, 455, 260, 55);
        Rectangle settingrectangle = new Rectangle(695, 340, 205, 55);
        Rectangle helprectangle = new Rectangle(665, 225, 260, 55);
        Rectangle exitrectangle = new Rectangle(750, 115, 95, 55);
        float textX = Gdx.input.getX();
        float textY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (startrectangle.contains(textX,textY)) {
            currentSelection=0;
        }
        if (settingrectangle.contains(textX,textY)) {
            currentSelection=1;
        }
        if (screenmanager.getinputManager().leftClick()) {
            // Check if the touch coordinates are inside the rectangle
            if (startrectangle.contains(textX, textY)||settingrectangle.contains(textX, textY)||helprectangle.contains(textX, textY)|| exitrectangle.contains(textX,textY)) {
                selectOption();
            }
        }
    }

    private void updateCurrentSelection() {
        if (screenmanager.getinputManager().isUpKeyJustPressed()) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = 1;
            }
        }
        if (screenmanager.getinputManager().isDownKeyJustPressed()) {
            currentSelection++;
            if (currentSelection > 1) {
                currentSelection = 0;
            }
        }
        if (screenmanager.getinputManager().isEnterKeyJustPressed()) {
            selectOption();
        }
    }

    private void selectOption() {
        switch (currentSelection) {
            case 0:
                screenmanager.pushScreen(new GameScreen(game));
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
        screenmanager.getoutputManager().soundEnd(backgroundMusic);
    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }

        if (backgroundImage != null) {
            backgroundImage.dispose();
        }
    }
}
