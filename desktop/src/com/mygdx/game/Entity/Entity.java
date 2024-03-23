package com.mygdx.game.Entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Interfaces.ICollideable;
import com.mygdx.game.Interfaces.IMovement;
import com.mygdx.game.Managers.EntityManager;


public abstract class Entity extends EntityManager implements ICollideable, IMovement {
	private float xPos;
	private float yPos;
	private float xSpeed, ySpeed;
	private float width, height;
	private Color color;
	private boolean isAIControl;    // Flag to determine whether entity is AI controlled.
	private float rotation;
	
	public Entity() {
		setXSpeed(0);
		setYSpeed(0);
		setX(0);
		setY(0);
		setWidth(0);
		setHeight(0);
		color = Color.WHITE;
		setControl(false);
		setRotation(0);
	}

	public Entity(float xPos, float yPos, float xSpeed, float ySpeed, float width, float height, Color color,boolean isAIControl) {
		if(xPos < 0 || yPos < 0 || xSpeed < 0 || ySpeed < 0 || width < 0 || height < 0)
		{
			throw new IllegalArgumentException("Invalid input; negative values");
		}
		else if(width == 0 || height == 0)
		{
			throw new IllegalArgumentException("Invalid input for width and height; zero values");
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
		setColor(color);
	}

	public Entity(float x, float y, float xSpeed, float ySpeed, boolean aiFlag)
	{
		setX(x);
		setY(y);
		setXSpeed(xSpeed);
		setYSpeed(ySpeed);
		setControl(aiFlag);
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
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		if(width < 0 || width > Gdx.graphics.getWidth()) {
			throw new IllegalArgumentException("Invalid input; size cannot be greater than screen size or negative");
		}
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		if(height < 0 || height > Gdx.graphics.getHeight()) {
			throw new IllegalArgumentException("Invalid input; size cannot be greater than screen size or negative");
		}
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
	
	// Rotation Get and Set.
    public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public abstract void update();
	public abstract void render();
	public abstract void dispose();
}


