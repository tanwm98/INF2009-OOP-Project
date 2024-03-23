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
    private boolean ishit;
    float flickerTimer = 0;

    public Spaceship(String filePath, float posX, float posY,float speedX, float speedY,OrthographicCamera camera,boolean aiFlag,boolean Collideable)
    {
        tex = new Texture(Gdx.files.internal(filePath));
        super.setX(posX);
        super.setY(posY);
        super.setXSpeed(speedX);
        super.setYSpeed(speedY);
        super.setControl(aiFlag);
        super.setCollideable(Collideable);
        this.camera = camera;
        super.setWidth(tex.getWidth());
        super.setHeight(tex.getHeight());
    }
    public void update() {
    	ishit = false;
    }

    @Override
    public void render()
    {
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin(); // Start drawing
        if(ishit) {
            flickerTimer += Gdx.graphics.getDeltaTime();
            if (flickerTimer < 0.1f) {
                batch.setColor(1, 1, 1, 0); // Fully transparent
            } else if (flickerTimer < 0.2f) {
                batch.setColor(1, 1, 1, 1); // Fully opaque
            } else {
                flickerTimer = 0;
            }
        }
        else {
            batch.setColor(1, 1, 1, 1); // Fully opaque
        }
        getoutputManager().draw(batch,tex, getX(), getY(), tex.getWidth(),tex.getHeight()); // Draw the spaceship texture at the spaceship's position
        batch.end(); // End drawing
    }
    public void dispose()
    {

    }
    @Override
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
    	if(collide) {
        	ishit = true;
    	}
    	else {
    		ishit = false;
    	}
    }
}
