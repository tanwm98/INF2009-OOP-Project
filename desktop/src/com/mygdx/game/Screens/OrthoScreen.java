package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Managers.ScreenManager;

public class OrthoScreen implements Screen {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Texture backgroundTexture;
    private ScreenManager screenManager;
    private SpriteBatch batch;
    private float circleX = 40;
    private float circleY = 40;
    private float textureWidth;
    private float textureHeight;
    private float circleRadius = 40;
    private float backgroundScrollSpeed = 100; // Speed of BackgroundScroll, Adjust if want to test
    private float offsetX = 0; // Offset for the background X position
    //private boolean paused = false;
    //private float offsetY = 0;
    

    public OrthoScreen(MyGdxGame game) {
        this.game = game;
        screenManager = new ScreenManager(game);
    }
    

    @Override
    public void show() {
    	// Camera dimensions
        int cameraWidth = 1200; 
        int cameraHeight = 900;

        // Instantiate camera
        camera = new OrthographicCamera();
        camera.position.set(cameraWidth / 2f, cameraHeight / 2f, 0);
        camera.update();

        viewport = new ExtendViewport(cameraWidth, cameraHeight, camera); //ExtendViewport to maintain aspect ratio
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("starfield8_screamingBrainStudios.png"));
        textureWidth = backgroundTexture.getWidth();
        textureHeight = backgroundTexture.getHeight();
        circleX = circleRadius; 
        circleY = camera.position.y;
    }

    @Override
    public void render(float delta) {
    	handleInput(delta);
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 1, 1);
        shapeRenderer.circle(circleX, circleY, circleRadius);
        shapeRenderer.end();
    }
   
    private void handleInput(float delta) {
        float moveSpeed = 100 * delta; // Circle Speed

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            circleX += moveSpeed;
            
            if (circleX - circleRadius > camera.viewportWidth) {
                circleX = -circleRadius;
            }
        }
        // Math.max prevents circle from moving out of screen
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            circleX = Math.max(circleRadius, circleX - moveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            
            circleX = Math.min(camera.viewportWidth - circleRadius, circleX + moveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            circleY = Math.min(camera.viewportHeight - circleRadius, circleY + moveSpeed); 
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            circleY = Math.max(circleRadius, circleY - moveSpeed); 
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                screenManager.pushScreen(new MainMenuScreen(game));
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
