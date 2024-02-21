package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

public class Ball extends Entity {
    ShapeRenderer shape = new ShapeRenderer();

    private float size;
    private Color color = Color.WHITE;

    public Ball(){
        super();
    }

    public Ball(float x, float y, float size, float xSpeed, float ySpeed, Color color,boolean aiFlag)
        {
            super.setX(x);
            super.setY(y);
            this.size = size;
            super.setXSpeed(xSpeed);
            super.setYSpeed(ySpeed);
            super.setColor(color);
            super.setControl(aiFlag);
            super.setHeight(size);
        }

    @Override
    public void update() {
        System.out.printf("Ball pos X:%f and Y: %f\n",super.getX(),super.getY());

    }
    @Override
    public void render(){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(color);
        shape.circle(super.getX(),super.getY(),size);
        shape.end();
    }
    
    @Override
    public void collide(boolean collide){
    	if(collide) {
            super.setYSpeed(super.getYSpeed() * -1); //reverse y direction
    	}
    }

    public void move()
    {
        super.setX(super.getX() + super.getXSpeed());
        super.setY(super.getY() + super.getYSpeed());
        if(super.getX() - size / 2 < 0 || super.getX() + size / 2 > Gdx.graphics.getWidth())  //divide by 2 to get the center of the ball
        {
            super.setXSpeed(super.getXSpeed() * -1); //reverse x direction
        }
        if(super.getY() - size / 2 < 0 || super.getY() + size / 2 > Gdx.graphics.getHeight())
        {
            super.setYSpeed(super.getYSpeed() * -1); //reverse y direction
        }
    }
    public void dispose()
    {
        shape.dispose();
    }
    public void playerMovement()
    {

    }

}