package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class Spaceship extends Entity {

    private Texture tex;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float cameraWidth = 1200;
    private float cameraHeight = 900;
    private SpriteBatch batch;

    public Spaceship(String filePath, float posX, float posY, float speedX, float speedY,boolean aiFlag)
    {
        super.setX(posX);
        super.setY(posY);
        super.setXSpeed(speedX);
        super.setYSpeed(speedY);
        super.setControl(aiFlag);
        tex = new Texture(Gdx.files.internal(filePath));

    }
    public void update() {
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin(); // Start drawing
        getoutputManager().draw(batch,tex, getX(), getY()); // Draw the spaceship texture at the spaceship's position
        batch.end(); // End drawing
    }
    public void dispose()
    {

    }
    @Override
    public void move()
    {
        float delta = Gdx.graphics.getDeltaTime();
        float movespeed = 200 * delta;
        if(getinputManager().isRightKeyPressed())
        {
            setX(getX() + movespeed);
            if(getX() - tex.getWidth() / 2 > cameraWidth)
            {
                setX(-tex.getWidth() / 2); // Wrap around
            }
        }
        if(getinputManager().isLeftKeyPressed())
        {
            setX(Math.max(0, getX() - movespeed)); // Prevent from going off screen
        }
        if(getinputManager().isRightKeyPressed())
        {
            setX(Math.min(camera.viewportWidth - tex.getWidth(), getX() + movespeed)); // Prevent from going off screen
        }
        if(getinputManager().isUpKeyPressed())
        {
            setY(Math.min(camera.viewportHeight - tex.getHeight(), getY() + movespeed)); // Prevent from going off screen
        }
        if(getinputManager().isDownKeyPressed())
        {
            setY(Math.max(getX(), getY() - movespeed)); // Prevent from going off screen
        }
    }
    public void collide(boolean collide)
    {

    }
}
