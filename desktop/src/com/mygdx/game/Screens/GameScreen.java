package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Entity.*;
import com.mygdx.game.Managers.*;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private float backgroundScrollSpeed = 200; // Speed of BackgroundScroll
    private float offsetX = 0; // Offset for the background X position
    private Entity asteroid;
    private Entity spaceship;
    private Entity planet;
    private Entity satellite;
    private boolean isPaused = false;
    private Stage stage;
    private Skin skin;
    private boolean showDialog = false;
    private boolean pause = false;


    public GameScreen(MyGdxGame game) {
        try {
            this.game = game;
            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();
            gameOverFont = new BitmapFont();
            optionFont = new BitmapFont(); // Initialize font
            screenManager = new ScreenManager(game);
            this.player = new Player(this.game);
            playerControlManager = new PlayerControlManager();
            
            

        } catch (Exception e) {
            System.err.println("GameScreen not initialised due to:" + e.getMessage());
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
            this.player = player;
            playerControlManager = new PlayerControlManager();
        } catch (Exception e) {
            System.err.println("Game Screen not initialised due to:" + e.getMessage());
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
        if (backgroundMusic == null) {
        	backgroundMusic=OutputManager.getInstance().musicStart(1);
        }
        backgroundTexture = new Texture(Gdx.files.internal("Background/starfield.png"));
        textureWidth = backgroundTexture.getWidth();
        textureHeight = backgroundTexture.getHeight();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));
        InputAdapter keyProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    pauseGame();
                    return true;
                }
                return false;
            }
        };
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(keyProcessor); 
        inputMultiplexer.addProcessor(stage); 

        Gdx.input.setInputProcessor(inputMultiplexer); // Set the multiplexer as the input processor
        if (entityManager == null || entityManager.getEntities().isEmpty()) {
            setupGameEntities();
        }
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
        if (entityManager != null && !isGameOver && playerControlManager != null) {
            aiControlManager.moveEntities();
            entityManager.renderEntities(); //
            entityManager.updateEntities(delta);
            entityManager.detect();
            if (!pause) {
                playerControlManager.moveEntities();
            }
            game.getPlayer().drawPlayer();
            game.getPlayer().drawScore();
            if (collisionManager.detectCollision(spaceship, planet)) {
                if (!showDialog) {
                    showDialog = true; // Set the flag to true so the dialog won't be shown again
                    pause = true;
                    // Display dialog box
                    Dialog dialog = new Dialog("Planet Collision Detected!", skin) {
                        {
                            getTitleLabel().setAlignment(Align.center);
                        }
                    };
                    Label label = new Label("Your spaceship has collided with Mars, the 4th planet in the solar system!", skin);
                    label.setWrap(true);
                    dialog.getContentTable().add(label).width(Gdx.graphics.getWidth() / 2); // Set the width as per your requirement
                    dialog.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
                    dialog.button("Click to continue your adventure").addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            dialog.hide();
                            pause = false;
                            game.setScreen(new FinishScreen(game));
                        }
                    });
                    dialog.show(stage);
                }
            } else {
                showDialog = false; // Reset the flag when the spaceship is not colliding with the planet
            }
            if (game.getPlayer().getLives() == 0) {
                isGameOver = true;
                screenManager.setScreen(new GameOverScreen(game, screenManager));
            }
        }
        stage.act(delta);
        stage.draw();
    }
    private void setupGameEntities() {
        aiControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
        outputManager = OutputManager.getInstance();

        entityManager = new EntityManager(aiControlManager, collisionManager, outputManager, playerControlManager);
        spaceship = new Spaceship("Objects/Spaceship/Spaceship1.png", 0, Gdx.graphics.getHeight() / 2,
                500, camera, false, true, true, player, game);
        planet = new Planet("Objects/Planets/planet02.png",
                Gdx.graphics.getWidth() + 200, Gdx.graphics.getHeight() / 2,
                50, true); //set planet out of screen and slowly move in
        entityManager.addEntity(spaceship);
        playerControlManager.addEntity(spaceship);
        entityManager.addEntity(planet);
        aiControlManager.addEntity(planet);

        for (int i = 0; i < 4; i++) {
            float posX = MathUtils.random(300, Gdx.graphics.getWidth());
            float posY = MathUtils.random(0, Gdx.graphics.getHeight());
            float speedX = MathUtils.random(-5, 4);
            float speedY = MathUtils.random(-5, 4);
            asteroid = new Asteroid("Objects/Asteroid/asteroid01.png", posX, posY,
                    speedX, speedY, true, player, game);
            entityManager.addEntity(asteroid);
            aiControlManager.addEntity(asteroid);
        }
        for (int i = 0; i < 2; i++) {
            float posX = MathUtils.random(300, Gdx.graphics.getWidth());
            float posY = MathUtils.random(0, Gdx.graphics.getHeight());
            float speedX = MathUtils.random(-5, 4);
            float speedY = MathUtils.random(-5, 4);
            satellite = new Satellite(posX, posY, speedX, speedY, true, game, player); //set satellite out of screen and slowly move in
            entityManager.addEntity(satellite);
            aiControlManager.addEntity(satellite);
        }
    }
    
    private void pauseGame() {
    	ScreenManager.getInstance(game).pushScreen(new PauseScreen(game, ScreenManager.getInstance(game)));
    }
    
   

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
        if (backgroundMusic != null)
        {
        	backgroundMusic.dispose();
        }
    }

    @Override
    public void pause() {
        isPaused = true;
        
    }

    @Override
    public void resume() {
        isPaused = false;
        
    }

    @Override
    public void hide() {
    	OutputManager.getInstance().soundEnd(backgroundMusic);
    }
}
