package com.mygdx.game;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	private SpriteBatch batch;
    private Texture backgroundImage;
    private MyGdxGame game;
    private Music backgroundMusic;
    

    public GameScreen(MyGdxGame game) {
    	this.game = game;
        batch = new SpriteBatch();
        backgroundImage = new Texture("Star.png"); // Load the background image
    }

    @Override
    public void show() {
    	 // Load the music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Kuro.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin(); // Begin batch drawing

        // Draw the background image covering the entire screen
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        batch.end(); // End batch drawing
        
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            backToMainMenu();
        }

    }
    public void backToMainMenu() {
        // Use the 'game' instance that was passed in the constructor of GameScreen
        game.setScreen(new MainMenuScreen(game)); 
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
    	if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
        }
        
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
