package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public abstract class Entity extends EntityManager implements ICollideable{
	private float xPos;
	private float yPos;
	private int xSpeed, ySpeed;
	private boolean isAIControl;	// Flag to determine whether entity is AI controlled.

	public Entity()
	{
		setXSpeed(0);
		setYSpeed(0);
		setX(0);
		setY(0);
		setControl(false);

	}
	public Entity(int xSpeed, int ySpeed, float xPos, float yPos, boolean isAIControl) {
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
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getXSpeed() {
		return xSpeed;
	}
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	public int getYSpeed() {
		return ySpeed;
	}
	
	
	public abstract void update(Entity entity);
	public abstract void render();
	
}

