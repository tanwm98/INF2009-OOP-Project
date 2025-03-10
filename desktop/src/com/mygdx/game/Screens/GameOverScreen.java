package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Managers.ScreenManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameOverScreen implements Screen {
	private Texture gameOverImage;
    private MyGdxGame game;
    private Music backgroundMusic;
    private SpriteBatch batch;
    private BitmapFont optionFont;
    private int selectedOptions = 0;
    private float opacity = 0f; // Start fully transparent
    private float fadeSpeed = 0.5f; // Adjust for faster or slower fade
    
    public GameOverScreen(MyGdxGame game, ScreenManager screenManager) {
        this.game = game;
	game.getPlayer().setLives(3);
	game.getPlayer().setScore(0);
    }

    @Override
    public void show() {
    	 gameOverImage = new Texture("Background/GameOver.png");
         batch = new SpriteBatch();
         optionFont = new BitmapFont();
         if (backgroundMusic == null) {
         	backgroundMusic=OutputManager.getInstance().musicStart(4);
         }
    }
    

    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	batch.begin();

    	// Game Over Fade
    	if (opacity < 1f) {
    		opacity += delta * fadeSpeed;
    		opacity = Math.min(opacity, 1f); 
    	}

    	float imageWidth = Gdx.graphics.getWidth() * 0.5f;
    	float imageHeight = imageWidth * (gameOverImage.getHeight() / (float)gameOverImage.getWidth());
    	float gameOverImageX = (Gdx.graphics.getWidth() - imageWidth) / 2;
    	float gameOverImageY = (Gdx.graphics.getHeight() - imageHeight) / 2;

    	batch.setColor(1, 1, 1, opacity);
    	// Draw the game over image with the specified size and alpha for fade-in
    	OutputManager.getInstance().draw(batch,gameOverImage, gameOverImageX, gameOverImageY, imageWidth, imageHeight);
    	batch.setColor(1, 1, 1, 1); 
    	float exitPosX = 650.5f; 
    	float optionsPosY = 250.0f; 
    	OutputManager.getInstance().draw(batch, "Back to Menu", exitPosX, optionsPosY, selectedOptions == 1);

    	batch.end();
    	mouseSelection();
    	handleInputs();
    }
    
    private void mouseSelection() {
    	Rectangle exitrectangle = new Rectangle(645, 210, 290, 50);
    	float textX = Gdx.input.getX();
        float textY = Gdx.graphics.getHeight() - Gdx.input.getY();
        
        if (exitrectangle.contains(textX,textY)) {
        	selectedOptions = 1; //only light up when hovering top of text
        } else {
        	selectedOptions = 0;
        }
    	if (InputManager.getInstance().leftClick()) {
            if (exitrectangle.contains(textX, textY)) {
            	ScreenManager.getInstance(game).setScreen(new MainMenuScreen(game));
            }
    	}
    }

    private void handleInputs() {
    	if (InputManager.getInstance().isEnterKeyJustPressed()) {
            ScreenManager.getInstance(game).setScreen(new MainMenuScreen(game));
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
    	OutputManager.getInstance().soundEnd(backgroundMusic);
    }

    @Override
    public void dispose() {
    	gameOverImage.dispose();
    	batch.dispose();
        optionFont.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
    }
}
