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

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public Spaceship(String filePath, float posX, float posY, int width, int height,float speedX, float speedY,OrthographicCamera camera,boolean aiFlag)
    {
        tex = new Texture(Gdx.files.internal(filePath));
        super.setX(posX);
        super.setY(posY);
        super.setWidth(width);
        super.setHeight(height);
        super.setXSpeed(speedX);
        super.setYSpeed(speedY);
        super.setControl(aiFlag);
        this.camera = camera;

    }
    public void update() {
    }

    @Override
    public void render()
    {
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin(); // Start drawing
        getoutputManager().draw(batch,tex, getX(), getY(), getWidth(),getHeight()); // Draw the spaceship texture at the spaceship's position
        batch.end(); // End drawing
    }
    public void dispose()
    {
        tex.dispose();
        batch.dispose();
    }

    public void move()
    {
        float delta = Gdx.graphics.getDeltaTime();
        float moveSpeed = 200 * delta;

        if(getinputManager().isRightKeyPressed() && !getinputManager().isLeftKeyPressed())
        {
            setX(getX() + moveSpeed);
            if(getX() - getWidth() / 2 > Gdx.graphics.getWidth())
            {
                setX(-getWidth() / 2); // Wrap around
            }
        }
        else if(getinputManager().isLeftKeyPressed())
        {
            setX(Math.max(0, getX() - moveSpeed)); // Prevent from going off screen
        }

        if(getinputManager().isUpKeyPressed() && !getinputManager().isDownKeyPressed())
        {
            setY(Math.min(Gdx.graphics.getHeight() - getHeight(), getY() + moveSpeed)); // Prevent from going off screen
        }
        else if(getinputManager().isDownKeyPressed())
        {
            setY(Math.max(0, getY() - moveSpeed)); // Prevent from going off screen
        }
    }
    public void collide(boolean collide)
    {

    }
}
