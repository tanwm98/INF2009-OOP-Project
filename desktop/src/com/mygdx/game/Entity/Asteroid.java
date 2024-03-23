package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid extends Entity {
	private Texture tex;
	private float sizeX, sizeY;
	private float rotation;
	
	SpriteBatch batch = new SpriteBatch();
	
    public Asteroid() {

    }
    
    public Asteroid(String filePath, float sizeX, float sizeY) {
    	super();
		tex = new Texture(Gdx.files.internal(filePath));
		this.sizeX = sizeX;
		this.sizeY = sizeY;
    }
	public Asteroid(String filePath, float posX, float posY, float width, float height, float rotation, float speedX, float speedY,boolean aiFlag)
	{
		super.setX(posX);
		super.setY(posY);
		super.setXSpeed(speedX);
		super.setYSpeed(speedY);
		super.setControl(aiFlag);
		super.setWidth(width);
		super.setHeight(height);
		this.rotation = rotation;
		tex = new Texture(Gdx.files.internal(filePath));
	}
	public Texture getTexture() {
		return tex;
	}
	
	public void setTexture(Texture t) {
		tex = t;
	}
	
	public void setSizeX(float x) {
		this.sizeX = x;
	}
	
	public void setY(float y) {
		this.sizeY = y;
	}
	
	public float getSizeX() {
		return sizeX;
	}
	
	public float getSizeY() {
		return sizeY;
	}
	public void update() {
        System.out.printf("Asteroid pos X:%f and Y: %f\n",super.getX(),super.getY());
    }

    public void render() {
    	batch.begin();
    	getoutputManager().draw(batch, getTexture(), super.getX(), super.getY(), super.getWidth(), super.getHeight());
		batch.end();
    }

    public void dispose() {
    	batch.dispose();
    }

    public void move() {
		super.setX(super.getX() + super.getXSpeed());
		super.setY(super.getY() + super.getYSpeed());
		if(super.getX() - getSizeY() / 2 < 0 || super.getX() + getSizeX() / 2 > Gdx.graphics.getWidth())
		//divide by 2 to get the center of the ball
		{
			super.setXSpeed(super.getXSpeed() * -1); //reverse x direction
		}
		if(super.getY() - getSizeY() / 2 < 0 || super.getY() + getSizeX() / 2 > Gdx.graphics.getHeight())
		{
			super.setYSpeed(super.getYSpeed() * -1); //reverse y direction
		}
    }

    public void collide(boolean collide) {

    }

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
}