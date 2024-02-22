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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AIControlManager;
import com.mygdx.game.InputManager;
import com.mygdx.game.Entity.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.OutputManager;
import com.mygdx.game.Player;


public class GameScreen implements Screen {
	private SpriteBatch batch;
    private Texture backgroundImage;
    private MyGdxGame game;
    private Music backgroundMusic;
    private ShapeRenderer shapeRenderer;

    private EntityManager entityManager;
    private AIControlManager aiControlManager;
    private Player player;
    private Entity ball;
    private Entity paddle;
    private int blockWidth = 63, blockHeight = 20;//set the width and height of the blocks

    private boolean isGameOver = false;
    private BitmapFont gameOverFont;
    private BitmapFont optionFont;
    private BitmapFont livesFont;
    private BitmapFont scoreFont;
    private int selectedOptions = 0;
    
    OutputManager outputManager=new OutputManager();
    InputManager inputManager=new InputManager();


    public GameScreen(MyGdxGame game) {
        try {
            this.game = game;
            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();
            gameOverFont = new BitmapFont();
            optionFont = new BitmapFont(); // Initialize a separate font for options if needed
            player = new Player();
            livesFont = new BitmapFont();
            scoreFont = new BitmapFont();
            selectedOptions = 0;
        }
        catch(Exception e) {
            System.err.println("Game screen not initialised due to:" + e.getMessage());
        }

    }

    @Override
    public void show() {
    	 // Load the music
    	entityManager = new EntityManager();
    	outputManager.musicStart(false);
        setupGameEntities();
        player = new Player();
        
    }
    private void setupGameEntities() {
        aiControlManager = new AIControlManager();
        entityManager = new EntityManager(aiControlManager);

        paddle = new Paddle(100, 20, 300, 100, 20, Color.WHITE, false);
        ball = new Ball( 0,30, 10, 5, 5, Color.WHITE, true);
        entityManager.addEntity(ball);
        entityManager.addEntity(paddle);
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                entityManager.addEntity(new Block(x, y, blockWidth, blockHeight, Color.WHITE));
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (!isGameOver) {
        update(delta);
        
        batch.begin();

        outputManager.draw(batch, "Lives: " + player.getLives(), 0, 100);
        outputManager.draw(batch, "Score: " + player.getScore(), 0, 50);
        batch.end(); 
        
        if (entityManager != null) {
            aiControlManager.moveEntities();
            entityManager.renderEntities(); //
            entityManager.detect();
            paddle.move();
        }
    } else {
    	batch.begin();
    	GlyphLayout gameOverLayout = new GlyphLayout(gameOverFont, "Game Over!");
    	float gameOverPosX = (Gdx.graphics.getWidth() - gameOverLayout.width) / 2;
    	float gameOverPosY = (Gdx.graphics.getHeight() / 2) + gameOverLayout.height;
    	outputManager.draw(batch, gameOverLayout, gameOverPosX, gameOverPosY);

    	
    	GlyphLayout retryLayout = new GlyphLayout(optionFont, "Retry?");
    	GlyphLayout exitLayout = new GlyphLayout(optionFont, "Back to Menu");

    	// Gaps between the Text
    	float optionsWidthTotal = retryLayout.width + exitLayout.width + 20; 
    	float retryPosX = (Gdx.graphics.getWidth() - optionsWidthTotal) / 2;
    	float exitPosX = retryPosX + retryLayout.width + 20;
    	float optionsPosY = gameOverPosY - gameOverLayout.height - 40; 

		// Retry
    	outputManager.draw(batch, "Retry?", retryPosX, optionsPosY,selectedOptions==0);


    	// Exit
    	outputManager.draw(batch,"Back to Menu", exitPosX, optionsPosY, selectedOptions==1);

        batch.end();
        handleInputs();
    }
    
    // ESC to Main Menu
    if (!isGameOver && Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
        backToMainMenu();
    }
}
    private void handleInputs() {
        if (isGameOver) {
            if (inputManager.isLeftKeyJustPressed() || inputManager.isRightKeyJustPressed()) {
            	selectedOptions = (selectedOptions + 1) % 2; // Toggle between options
            }

            if (inputManager.isEnterKeyJustPressed()) {
                if (selectedOptions == 0) {
                    resetGame(); // Reset the game
                } else if (selectedOptions == 1) {
                    backToMainMenu();
                }
            }
        }
    }
    
    private void resetGame() {
        isGameOver = false;
        setupGameEntities();
        player.setLives(3);
        player.setScore(0);
   
    }
    
    private void update() {
        if(player.getLives() == 0) {
            isGameOver = true;
        }
        else if(ball.getY() <= 0) {
            player.setLives(player.getLives() - 1);
        }
        else
        {
            player.addScore(10);
        }
	}

	public void backToMainMenu() {
        game.getScreenManager().pushScreen(new MainMenuScreen(game));
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
        if (backgroundImage != null) {
            backgroundImage.dispose();
        }
        if (gameOverFont != null) {
            gameOverFont.dispose();
        }
    	if (backgroundMusic != null) {
        backgroundMusic.dispose();
    	}
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
        if (optionFont != null) {
            optionFont.dispose();
        }
        if (entityManager != null) {
            entityManager.dispose();
        }
    }
}
