package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;


public abstract class Entity extends EntityManager implements ICollideable, IMovement{
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
		color = Color.WHITE;
		setControl(false);
	}
	public Entity(float xSpeed, float ySpeed, float xPos, float yPos, boolean isAIControl) {
		setXSpeed(xSpeed);
		setYSpeed(ySpeed);
		setX(xPos);
		setY(yPos);
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
}

