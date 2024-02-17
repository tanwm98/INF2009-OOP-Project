package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Ball extends Entity {
    private int size;
    private Color color = Color.WHITE;
    public Ball(){
        super();
    }
    public void movement(){
        // Move the ball
    }
    public void update(Entity entity) {
        // Update the ball
    }
    public void render(){
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(color);
        shape.circle(super.getX(),super.getY(),size);
    }
    public void checkCollision(Paddle paddle)
    {
        if(super.getX() == paddle.getX() && super.getY() == paddle.getY())
        {
            color = Color.RED;
        }
    }

}
