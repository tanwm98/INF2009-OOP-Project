package com.mygdx.game.Entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Interfaces.ICollideable;
import com.mygdx.game.Interfaces.IMovement;


public abstract class Entity extends EntityManager implements ICollideable, IMovement {
	private float xPos;
	private float yPos;
	private float xSpeed, ySpeed;
	private float width,height;
	private Color color;
	private boolean isAIControl;	// Flag to determine whether entity is AI controlled.

	public Entity()
	{
		setXSpeed(0);
		setYSpeed(0);
		setX(0);
		setY(0);
		setWidth(0);
		setHeight(0);
		color = Color.WHITE;
		setControl(false);
	}
	public Entity(float xPos, float yPos,float xSpeed, float ySpeed,float width,float height, boolean isAIControl) {
		if(xPos < 0 || yPos < 0 || xSpeed < 0 || ySpeed < 0 || width < 0 || height < 0)
		{
			throw new IllegalArgumentException("Invalid input; negative values");
		}
		else if(xSpeed == 0 || ySpeed == 0 || width == 0 || height == 0)
		{
			throw new IllegalArgumentException("Invalid input; zero values");
		}
		else if(xPos > Gdx.graphics.getWidth() || yPos > Gdx.graphics.getHeight())
		{
			throw new IllegalArgumentException("Invalid input; position cannot be greater than screen size");
		}
		else if(width > Gdx.graphics.getWidth())
		{
			throw new IllegalArgumentException("Invalid input; size cannot be greater than screen size");
		}
		setXSpeed(xSpeed);
		setYSpeed(ySpeed);
		setX(xPos);
		setY(yPos);
		setWidth(width);
		setHeight(height);
		setControl(isAIControl);
	}
	
	// X position Getter and Setter
	public float getX() {
		return xPos;
	}
	public void setX(float xPos) {
		this.xPos = xPos;
	}
	
	// Y position Getter and Setter
	public float getY() {
		return yPos;
	}
	public void setY(float yPos) {
		this.yPos = yPos;
	}
	
	// Control Getter and Setter
	public boolean getControl() {
		return isAIControl;
	}
	public void setControl(boolean isAIControl) {
		this.isAIControl = isAIControl;
	}
	
	
	// Speed Getter and Setter
	public void setXSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}
	public float getXSpeed() {
		return xSpeed;
	}
	public void setYSpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	public float getYSpeed() {
		return ySpeed;
	}
	
	
	public abstract void update();
	public abstract void render();
	public abstract void dispose();

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isAIControl() {
		return isAIControl;
	}
	public void setAIControl(boolean isAIControl) {
		this.isAIControl = isAIControl;
	}
}


