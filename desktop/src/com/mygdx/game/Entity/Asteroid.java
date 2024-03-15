package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid extends Entity {
	private Texture tex;
	
	SpriteBatch batch = new SpriteBatch();
	
    public Asteroid() {

    }
    
    public Asteroid(String filePath) {
    	super();
		tex = new Texture(Gdx.files.internal(filePath));
    }
    
	public Texture getTexture() {
		return tex;
	}
	
	public void setTexture(Texture t) {
		tex = t;
	}
	
	public void update() {
        System.out.printf("Asteroid pos X:%f and Y: %f\n",super.getX(),super.getY());
    }

    public void render() {
    	batch.begin();
    	getoutputManager().draw(batch, getTexture(), super.getX(), super.getY());
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