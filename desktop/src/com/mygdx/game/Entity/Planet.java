package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Entity {
	private Texture texture;
	private float rotation;
	private float scaleFactor;
	private SpriteBatch batch = new SpriteBatch();

	
	public Planet(String fileName, float x, float y, float rotate, float scaleFactor, boolean aiFlag) {
		super();
		setX(x);
		setY(y);
		setTexture(new Texture(Gdx.files.internal(fileName)));
		setRotation(rotate);
		setScaleFactor(scaleFactor);
	}
	
	// Scale Factor (size of texture) Get and Set.
	public float getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(float scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	// Rotation Get and Set.
    public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void update() {
		System.out.println("Planet at:"+ this.getX()+ ","+ this.getY());
    }
    public void render()
    {
		Texture tex = this.getTexture();
		float x = this.getX();
		float y = this.getY();
		float texWidth = tex.getWidth();
		float texHeight = tex.getHeight();
		float originX = texWidth/2;
		float originY = texHeight/2;
		float scaleFactor = this.getScaleFactor();
		float rotate = this.getRotation();
    	
		batch.begin();
		getoutputManager().draw(batch, tex, x, y, originX, originY, texWidth, texHeight, scaleFactor, rotate);
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
