package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;

public class Planet extends Entity {
	private Texture tex;
	private SpriteBatch batch = new SpriteBatch();
	private Player player;

	private boolean isMoving = true;
	public Planet(String fileName, float x, float y, float Xspeed,boolean Collideable) {
		super();
		setX(x);
		setY(y);
		setTexture(tex = new Texture(Gdx.files.internal(fileName)));
		setXSpeed(Xspeed);
		super.setCollideable(Collideable);
		super.setWidth(tex.getWidth());
		super.setHeight(tex.getHeight());
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
		OutputManager.getInstance().draw(batch, tex, x, y, originX, originY, texWidth, texHeight, 1, rotate);
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
	public void update(float delta)
	{
		super.update(delta);
	}
	public void collide(boolean collide) {
		if (collide && super.getCollisionCD() <= 0) {
			OutputManager.getInstance().playsound("Music/sfx/correct_sfx.wav");
			super.setCollisionCD(2);
		}
	}
    public void setTexture(Texture t) {
    	this.tex = t;
    }
    public Texture getTexture() {
    	return this.tex;
    }
}
