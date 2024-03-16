package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Entity {
	private Texture texture;
	private float rotation;
	private SpriteBatch batch = new SpriteBatch();

	
	public Planet(String fileName, float rotate) {
		super();
		setX(-100);
		setY(-100);
		setTexture(new Texture(Gdx.files.internal(fileName)));
		setRotation(rotate);
	}
	
	// Rotation Get and Set.
    public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void update() {

    }
    public void render()
    {
		Texture tex = this.getTexture();
		float x = this.getX();
		float y = this.getY();
		float texWidth = this.getTexture().getWidth();
		float texHeight = this.getTexture().getHeight();
		float originX = (this.getTexture().getWidth())/2;
		float originY = (this.getTexture().getHeight())/2;

		float rotate = this.getRotation();
    	
		batch.begin();
		getoutputManager().draw(batch, tex, x, y, originX, originY, texWidth, texHeight, rotate);
		batch.end();
    }
    public void dispose()
    {
    	this.dispose();
    }
    public void move()
    {
		setRotation(getRotation() + 1);
    }
    public void collide(boolean collide)
    {

    }
    
    
    public void setTexture(Texture t) {
    	this.texture = t;
    }
    
    
    public Texture getTexture() {
    	return this.texture;
    }
    
    
}
