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

public class OrthoScreen implements Screen {
    private MyGdxGame game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private float circleX = 50;
    private float circleY = 50;
    private float textureWidth;
    private float textureHeight;
    private float circleRadius = 50; 

    public OrthoScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Camera dimensions
        int cameraWidth = 1200; 
        int cameraHeight = 900;

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(cameraWidth, cameraHeight, camera); // Use ExtendViewport to maintain aspect ratio
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("Galaxy.jpg"));
        textureWidth = backgroundTexture.getWidth();
        textureHeight = backgroundTexture.getHeight();

        // Position the camera at the center of the world
        camera.position.set(cameraWidth / 2f, cameraHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        // Calculate the position offsets for rendering the background image
        float offsetX = -(circleX % textureWidth);
        float offsetY = -(circleY % textureHeight);

        // Draw the background image based on the circle's position
        for (float x = offsetX; x < Gdx.graphics.getWidth(); x += textureWidth) {
            for (float y = offsetY; y < Gdx.graphics.getHeight(); y += textureHeight) {
                batch.draw(backgroundTexture, x, y);
            }
        }
        batch.end();

        float startPosition = 50;
        float circleCenterX = startPosition + 20 / 2f;  
        float circleCenterY = (Gdx.graphics.getHeight() - 20) / 2f;
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 1, 1); //Color
        shapeRenderer.circle(circleCenterX, circleCenterY, 20);
        shapeRenderer.end();
    }
   
 private void handleInput() {
     float moveSpeed = 120 * Gdx.graphics.getDeltaTime();

     if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
         circleX += moveSpeed;
     }

     if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
         circleY += moveSpeed;
         // Ensure the circle doesn't move off the top edge of the screen
         circleY = Math.min(circleY, viewport.getWorldHeight() - circleRadius);
     }

     if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
         circleY -= moveSpeed;
         // Ensure the circle doesn't move off the bottom edge of the screen
         circleY = Math.max(circleY, circleRadius);
     }
     camera.update();
 }
    @Override
    public void resize(int width, int height) {
        // Update viewport dimensions
    	viewport.update(width, height);
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
