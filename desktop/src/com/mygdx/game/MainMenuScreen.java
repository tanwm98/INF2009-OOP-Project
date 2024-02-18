package com.mygdx.game;

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

public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture backgroundImage;
    private MyGdxGame game;
    private int currentSelection = 0;
    private Music backgroundMusic;
    

 
    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        backgroundImage = new Texture("DarkSpace.jpg"); // Load Background Image
    }

    @Override
    public void show() {
    	 // Load the music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Toothless.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    
        batch.begin();

        // Background image covers the whole screen
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Calculate the center of the screen
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        float lineHeight = font.getLineHeight();
        float gap = 1 * Gdx.graphics.getPpcY(); // Initialize Gap between lines
        float startY = centerY + lineHeight;

        GlyphLayout layout = new GlyphLayout(); //Calculating text width

        // Draw "Start Game" centered
        String startText = "Start Game";
        layout.setText(font, startText);
        float startGameWidth = layout.width;
        float startX = centerX - startGameWidth / 2; // To Center the text
        font.setColor(currentSelection == 0 ? Color.YELLOW : Color.WHITE);
        font.draw(batch, startText, startX, startY);
        startY -= lineHeight + gap;

       

        // Draw "Exit" centered
        String exitText = "Exit";
        layout.setText(font, exitText);
        float exitWidth = layout.width;
        startX = centerX - exitWidth / 2;
        font.setColor(currentSelection == 1 ? Color.RED : Color.WHITE);
        font.draw(batch, exitText, startX, startY);

        font.setColor(Color.WHITE);


        batch.end(); // End batch drawing
        
        updateCurrentSelection();
    }


    private void updateCurrentSelection() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = 1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentSelection++;
            if (currentSelection > 1) {
                currentSelection = 0;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            selectOption();
        }
    }
    
    // Option Selected will prompt a Use Case
    private void selectOption() {
    	switch (currentSelection) {
        case 0:
        		game.setScreen(new GameScreen(game)); // New Game Screen
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
    	backgroundMusic.stop();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        backgroundImage.dispose();
        backgroundMusic.dispose();
    }
}
