package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Ball extends Entity {
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
        }

    public void movement(){
        if(super.getControl())
        {
            //AI movement
        }
    }
    @Override
    public void update() {
        super.setX(super.getX() + super.getXSpeed());
        super.setY(super.getY() + super.getYSpeed());

    }
    @Override
    public void render(){
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(color);
        shape.circle(super.getX(),super.getY(),size);
        shape.end();
    }
    public void checkCollision(Paddle paddle)
    {
        if(super.getX() == paddle.getX() && super.getY() == paddle.getY())
        {
            color = Color.RED;
        }
    }
    public void collide(Entity e, Entity e2){

    }

    public void move()
    {

    }

    public void playerMovement()
    {

    }

}
