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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {
	private SpriteBatch batch;
    private Texture backgroundImage;
    private MyGdxGame game;
    private Music backgroundMusic;
    private ShapeRenderer shapeRenderer;
    
    private ScreenManager screenManager;
    private EntityManager entityManager;
    private Player player;
    private Entity ball;
    private Entity paddle;
    private int blockWidth = 63, blockHeight = 20; //set the width and height of the blocks
    private boolean isGameOver = false;
    private BitmapFont gameOverFont;
    private BitmapFont optionFont;
    private int selectedOptions = 0;
    
    

    public GameScreen(MyGdxGame game) {
    	this.game = game;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        gameOverFont = new BitmapFont();
        optionFont = new BitmapFont(); // Initialize a separate font for options if needed
        selectedOptions = 0;
        
    }

    @Override
    public void show() {
    	 // Load the music
    	entityManager = new EntityManager();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Wii.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        setupGameEntities();
        
    }
    private void setupGameEntities() {
        entityManager = new EntityManager();
        ball = new Ball(50, 20, 10, 5, 5, Color.WHITE, true);
        paddle = new Paddle(100, 20, 300, 100, 20, Color.WHITE, false);

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
        batch.end(); 
        
        if (entityManager != null) {
            entityManager.moveEntities();
            entityManager.renderEntities(); //
            entityManager.detect();
        }
    } else {
    
    	batch.begin();
    	GlyphLayout gameOverLayout = new GlyphLayout(gameOverFont, "Game Over!");
    	float gameOverPosX = (Gdx.graphics.getWidth() - gameOverLayout.width) / 2;
    	float gameOverPosY = (Gdx.graphics.getHeight() / 2) + gameOverLayout.height;
    	gameOverFont.draw(batch, gameOverLayout, gameOverPosX, gameOverPosY);

    	
    	GlyphLayout retryLayout = new GlyphLayout(optionFont, "Retry?");
    	GlyphLayout exitLayout = new GlyphLayout(optionFont, "Back to Menu");

    	// Gaps between the Text
    	float optionsWidthTotal = retryLayout.width + exitLayout.width + 20; 
    	float retryPosX = (Gdx.graphics.getWidth() - optionsWidthTotal) / 2;
    	float exitPosX = retryPosX + retryLayout.width + 20;
    	float optionsPosY = gameOverPosY - gameOverLayout.height - 40; 

    	// Retry
    	optionFont.setColor(selectedOptions == 0 ? Color.YELLOW : Color.WHITE);
    	optionFont.draw(batch, "Retry?", retryPosX, optionsPosY);

    	// Exit
    	optionFont.setColor(selectedOptions == 1 ? Color.BLUE : Color.WHITE);
    	optionFont.draw(batch, "Back to Menu", exitPosX, optionsPosY);
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
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            	selectedOptions = (selectedOptions + 1) % 2; // Toggle between options
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
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
   
    }
    
    private void update(float delta) {
    	if (ball.getY() <= 0) {
            isGameOver = true;
        }
		
	}

	public void backToMainMenu() {
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
        if (gameOverFont != null) {
            gameOverFont.dispose();
        }
        
    }
}
