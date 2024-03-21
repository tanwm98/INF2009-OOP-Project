//package com.mygdx.game.Screens;
//
//import com.badlogic.gdx.Screen;
//
//
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.mygdx.game.Managers.AIControlManager;
//import com.mygdx.game.Entity.*;
//import com.mygdx.game.Managers.EntityManager;
//import com.mygdx.game.Managers.ScreenManager;
//import com.mygdx.game.MyGdxGame;
//import com.mygdx.game.Player;
//
//
//public class GameScreen implements Screen {
//	private SpriteBatch batch;
//    private Texture backgroundImage;
//    private MyGdxGame game;
//    private Music backgroundMusic;
//    private ShapeRenderer shapeRenderer;
//    private ScreenManager screenManager;
//    private EntityManager entityManager;
//    private AIControlManager aiControlManager;
//    private Player player;
//    private Entity ball;
//    private Entity paddle;
//    private int blockWidth = 63, blockHeight = 20;//set the width and height of the blocks
//
//    private boolean isGameOver = false;
//    private BitmapFont gameOverFont;
//    private BitmapFont optionFont;
//
//
//
//    public GameScreen(MyGdxGame game) {
//        try {
//            this.game = game;
//            batch = new SpriteBatch();
//            shapeRenderer = new ShapeRenderer();
//            gameOverFont = new BitmapFont();
//            optionFont = new BitmapFont(); // Initialize font
//            player = new Player();
//            screenManager = new ScreenManager(game);
//
//        }
//        catch(Exception e) {
//            System.err.println("Game screen not initialised due to:" + e.getMessage());
//        }
//
//    }
//
//    @Override
//    public void show() {
//    	entityManager = new EntityManager();
//    	backgroundMusic = screenManager.getoutputManager().musicStart(false);
//        setupGameEntities();
//        player = new Player();
//
//    }
//    private void setupGameEntities() {
//        aiControlManager = new AIControlManager();
//        entityManager = new EntityManager(aiControlManager);
//
//        paddle = new Paddle(100, 20, 300, 100, 20, Color.WHITE, false);
//        ball = new Ball( 0,30, 10, 5, 5, Color.WHITE, true);
//        entityManager.addEntity(ball);
//        entityManager.addEntity(paddle);
//        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
//            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
//                entityManager.addEntity(new Block(x, y, blockWidth, blockHeight, Color.WHITE));
//            }
//        }
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 0, 0);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        if (!isGameOver) {
//        update();
//
//        batch.begin();
//
//        screenManager.getoutputManager().draw(batch, "Lives: " + player.getLives(), 0, 100);
//        screenManager.getoutputManager().draw(batch, "Score: " + player.getScore(), 0, 50);
//        batch.end();
//
//        if (entityManager != null) {
//            aiControlManager.moveEntities();
//            entityManager.renderEntities(); //
//            entityManager.detect();
//            paddle.move();
//        }
//    } else {
//    	if (isGameOver) {
//    		screenManager.setScreen(new GameOverScreen(game, screenManager));
//    	}
//    }
//}
//
//    public void resetGame() {
//        // Reset all entities and state
//        isGameOver = false;
//        setupGameEntities();
//        player.setLives(3);
//        player.setScore(0);
//
//    }
//
//    private void update() {
//        if(player.getLives() == 0) {
//            isGameOver = true;
//        }
//        else if(ball.getY() <= 0) {
//            player.setLives(player.getLives() - 1);
//        }
//        else
//        {
//            player.addScore(10);
//        }
//	}
//
//	public void backToMainMenu() {
//		screenManager.pushScreen(new MainMenuScreen(game));
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//    	screenManager.getoutputManager().soundEnd(backgroundMusic);
//    }
//
//    @Override
//    public void dispose() {
//    	if (batch != null) {
//            batch.dispose();
//        }
//        if (backgroundImage != null) {
//            backgroundImage.dispose();
//        }
//        if (gameOverFont != null) {
//            gameOverFont.dispose();
//        }
//    	if (backgroundMusic != null) {
//        backgroundMusic.dispose();
//    	}
//        if (backgroundMusic != null) {
//            backgroundMusic.dispose();
//        }
//        if (shapeRenderer != null) {
//            shapeRenderer.dispose();
//        }
//        if (optionFont != null) {
//            optionFont.dispose();
//        }
//        if (entityManager != null) {
//            entityManager.dispose();
//        }
//    }
//}
