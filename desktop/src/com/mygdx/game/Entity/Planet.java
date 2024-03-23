package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet extends Entity {
	private Texture tex;
	private float rotation;
	private float scaleFactor;
	private SpriteBatch batch = new SpriteBatch();
	private boolean isVisible = false;
	private boolean isMoving = false;

	public Planet(String fileName, float x, float y, float rotate, boolean aiFlag,boolean Collideable) {
		super();
		setX(x);
		setY(y);
		setTexture(tex = new Texture(Gdx.files.internal(fileName)));
//		setRotation(rotate);
		setXSpeed(rotate);
		setScaleFactor(scaleFactor);
		setControl(aiFlag);
		super.setCollideable(Collideable);
		super.setWidth(tex.getWidth());
		super.setHeight(tex.getHeight());
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
    }
    public void render()
    {
		tex = this.getTexture();
		float x = this.getX();
		float y = this.getY();
		float texWidth = tex.getWidth();
		float texHeight = tex.getHeight();
		float originX = texWidth/2;
		float originY = texHeight/2;
		float rotate = this.getRotation();
		batch.begin();
		getoutputManager().draw(batch, tex, x, y, originX, originY, texWidth, texHeight, 1, rotate);
		batch.end();

    }
    public void dispose()
    {
    	this.dispose();
    }
    public void move()
    {
		float screenWidth = Gdx.graphics.getWidth();
		if (getX() + getWidth() <= screenWidth) { // If the planet has reached the right edge of the screen
			setX(screenWidth - getWidth()); // Fix the planet's X position at the edge
		} else {
			float delta = Gdx.graphics.getDeltaTime();
			float moveSpeed = getXSpeed() * delta;
			setX(getX() - moveSpeed); // Continue moving the planet to the left
		}
    }
    public void collide(boolean collide)
    {
    	//Transition to trivia screen
    }

    public void setTexture(Texture t) {
    	this.tex = t;
    }
    public Texture getTexture() {
    	return this.tex;
    }
	public void setVisible(boolean visible) {
		isVisible = visible;
	}

}
