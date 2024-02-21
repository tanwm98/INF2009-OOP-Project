package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.OutputManager;

public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture backgroundImage;
    private MyGdxGame game;
    private int currentSelection = 0;

    OutputManager outputManager=new OutputManager();
    InputManager inputManager=new InputManager();

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        backgroundImage = new Texture("DarkSpace.jpg");
    }

    OutputManager output=new OutputManager();
    @Override
    public void show() {
    	outputManager.musicStart(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        outputManager.draw(batch,backgroundImage,0,0);
        //batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Calculate the center of the screen
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        float lineHeight = font.getLineHeight();
        float gap = 1 * Gdx.graphics.getPpcY(); // Initialize Gap between lines
        float startY = centerY + lineHeight;

        GlyphLayout layout = new GlyphLayout(); //Calculating text width

        // "Start Game" Centered
        String startText = "Start Game";
        layout.setText(font, startText);
        float startGameWidth = layout.width;
        float startX = centerX - startGameWidth / 2; // To Center the text
        outputManager.draw(batch, startText, startX, startY,currentSelection == 0);
        startY -= lineHeight + gap;
        //font.setColor(currentSelection == 0 ? Color.YELLOW : Color.WHITE);
        //font.draw(batch, startText, startX, startY);

        // "Exit" centered
        String exitText = "Exit";
        layout.setText(font, exitText);
        float exitWidth = layout.width;
        startX = centerX - exitWidth / 2;
        outputManager.draw(batch,exitText, startX, startY,currentSelection == 1);
        //font.setColor(currentSelection == 1 ? Color.RED : Color.WHITE);
        //font.draw(batch, exitText, startX, startY);


        font.setColor(Color.WHITE);
        batch.end(); // 
        updateCurrentSelection();
    }


    private void updateCurrentSelection() {
        if (inputManager.isUpKeyJustPressed()) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = 1;
            }
        }
        if (inputManager.isDownKeyJustPressed()) {
            currentSelection++;
            if (currentSelection > 1) {
                currentSelection = 0;
            }
        }
        if (inputManager.isEnterKeyJustPressed()) {
            selectOption();
        }
    }

    // Option Selected will prompt a Use Case
    private void selectOption() {
        switch (currentSelection) {
            case 0:
                game.getScreenManager().pushScreen(new GameScreen(game));
                break;
            case 1:
                Gdx.app.exit();
                break;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    	outputManager.soundEnd();
    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
        if (font != null) {
            font.dispose();
        }
        if (backgroundImage != null) {
            backgroundImage.dispose();
        }
    }
}
