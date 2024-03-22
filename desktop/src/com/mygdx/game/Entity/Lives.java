package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lives extends Entity {
	private Texture tex;
	private float sizeX, sizeY;
	
	SpriteBatch batch = new SpriteBatch();
	
    public Lives() {

    }
    
    public Lives(String filePath, float sizeX, float sizeY) {
    	super();
		tex = new Texture(Gdx.files.internal(filePath));
		this.sizeX = sizeX;
		this.sizeY = sizeY;
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
    	getoutputManager().draw(batch, getTexture(), super.getX(), super.getY(), getSizeX(), getSizeY());
		batch.end();
    }

    public void dispose() {
    	batch.dispose();
    }

    public void move() {

    }

    public void collide(boolean collide) {

    }
}
