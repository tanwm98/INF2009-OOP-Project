package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Entity.*;
import com.mygdx.game.Managers.*;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Player;

public class GameScreen implements Screen {
    private MyGdxGame game;
    private boolean isGameOver = false;
    private BitmapFont gameOverFont;
    private BitmapFont optionFont;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Texture backgroundTexture;
    private Music backgroundMusic;
    private EntityManager entityManager;
    private Player player;
    private PlayerControlManager playerControlManager;
    private CollisionManager collisionManager;
    private AIControlManager aiControlManager;
    private ScreenManager screenManager;
    private OutputManager outputManager;
    private SpriteBatch batch;
    private float textureWidth;
    private float textureHeight;
    private float backgroundScrollSpeed = 200; // Speed of BackgroundScroll, Adjust if want to test
    private float offsetX = 0; // Offset for the background X position
    private Entity asteroid;
    private Entity spaceship;
    private Entity planet;

    public GameScreen(MyGdxGame game) {
        try {
            this.game = game;
            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();
            gameOverFont = new BitmapFont();
            optionFont = new BitmapFont(); // Initialize font
            screenManager = new ScreenManager(game);
            playerControlManager = new PlayerControlManager(player,spaceship);
            player = new Player();

        } catch (Exception e) {
            System.err.println("OrthoScreen not initialised due to:" + e.getMessage());
        }
    }

    public GameScreen(MyGdxGame game, Player player) {
        try {
            this.game = game;
            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();
            gameOverFont = new BitmapFont();
            optionFont = new BitmapFont(); // Initialize font
            screenManager = new ScreenManager(game);
            playerControlManager = new PlayerControlManager(player,spaceship);
            this.player = player;
        } catch (Exception e) {
            System.err.println("OrthoScreen not initialised due to:" + e.getMessage());
        }
    }

    @Override
    public void show() {
        Gdx.graphics.setContinuousRendering(true);
        // Camera dimensions
        int cameraWidth  = Gdx.graphics.getWidth();
        int cameraHeight = Gdx.graphics.getHeight();
        // Instantiate camera
        camera = new OrthographicCamera();
        camera.position.set( cameraWidth / 2f, cameraHeight / 2f, 0);
        camera.update();
        viewport = new ExtendViewport(cameraWidth, cameraHeight, camera); //ExtendViewport to maintain aspect ratio
        backgroundMusic = screenManager.getoutputManager().musicStart(false);
        backgroundTexture = new Texture(Gdx.files.internal("Background/starfield.png"));
        textureWidth = backgroundTexture.getWidth();
        textureHeight = backgroundTexture.getHeight();
        setupGameEntities();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        offsetX -= delta * backgroundScrollSpeed; // Background scrolls right
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Draw the background by tiling it across the screen
        for (float x = offsetX % textureWidth - textureWidth; x < camera.viewportWidth; x += textureWidth) {
            for (float y = 0; y < camera.viewportHeight; y += textureHeight) {
                batch.draw(backgroundTexture, x, y);
            }
        }
        batch.end();
        if (entityManager != null && !isGameOver && playerControlManager != null)
        {
            aiControlManager.moveEntities();
            entityManager.renderEntities(); //
            entityManager.detect();
            playerControlManager.moveEntities();
            player.drawPlayer();
            player.drawScore();
        }
        else {
            if (isGameOver) {
                screenManager.setScreen(new GameOverScreen(game, screenManager));
            }
        }
    }
    private void setupGameEntities() {
        aiControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
        outputManager = screenManager.getoutputManager();
        entityManager = new EntityManager(aiControlManager, collisionManager,outputManager);
        entityManager = new EntityManager(aiControlManager, collisionManager,playerControlManager);
        spaceship = new Spaceship("Objects/Spaceship/Spaceship1.png",0, Gdx.graphics.getHeight() / 2,
                500,camera,false,true,true,player,game);
        planet = new Planet("Objects/Planets/planet02.png",
                Gdx.graphics.getWidth()+500, Gdx.graphics.getHeight() / 2,
                50, true,true); //set planet out of screen and slowly move in
        entityManager.addEntity(spaceship);
        entityManager.addEntity(planet);
        for(int i = 0; i < 3; i++) {
            float posX = MathUtils.random(300, Gdx.graphics.getWidth());
            float posY = MathUtils.random(0, Gdx.graphics.getHeight());
            float speedX = MathUtils.random(-5, 5);
            float speedY = MathUtils.random(-5, 5);
            asteroid = new Asteroid("Objects/Asteroid/asteroid01.png", posX, posY,
                    speedX, speedY, true, true);
            entityManager.addEntity(asteroid);
        }
    }

    // private void pauseGameAndShowMenu()
    //this.pause();
    //PauseMenu pauseMenu = new PauseMenu(game, game.getScreenManager());
    //pauseMenu.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        backgroundTexture.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
