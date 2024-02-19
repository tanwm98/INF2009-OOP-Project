package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Ball extends Entity {
    ShapeRenderer shape = new ShapeRenderer();

    private int size;
    private Color color = Color.WHITE;

    public Ball(){
        super();
    }

    public Ball(int x, int y, int size, int xSpeed, int ySpeed, boolean aiFlag)
        {
            super.setX(x);
            super.setY(y);
            this.size = size;
            super.setXSpeed(xSpeed);
            super.setYSpeed(ySpeed);
            super.setControl(aiFlag);
            super.setHeight(size);
        }

    @Override
    public void update() {
        super.setX(super.getX() + super.getXSpeed());
        super.setY(super.getY() + super.getYSpeed());

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
    	if(collide == true) {
        	color = Color.RED;
    	}
    	else {
    		color = Color.WHITE;
    	}
    }

    public void move()
    {


    }
    public void dispose()
    {
        shape.dispose();
    }
    public void playerMovement()
    {

    }

}
