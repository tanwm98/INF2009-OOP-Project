package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Paddle extends Entity {
    ShapeRenderer shape = new ShapeRenderer();
    private Color color = Color.WHITE;
    private int width,height;
    public Paddle(){
        super.setXSpeed(0);
        super.setYSpeed(0);
        super.setX(0);
        super.setY(0);
        super.setControl(false);
    }
    public Paddle(float x, float y, float xSpeed,int width, int height,boolean aiFlag)
    {
        super.setX(x);
        super.setY(y);
        super.setXSpeed(xSpeed);
        this.width = width;
        this.height = height;
        super.setControl(aiFlag);

    }
    @Override
    public void update() {
        System.out.printf("X: %f\n",super.getX());
    }
    @Override
    public void render(){

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(color);
        shape.rect(super.getX(),super.getY(),getWidth(),getHeight());
        shape.end();
    }
    public void move()
    {
        float delta = getXSpeed()*Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            if(super.getX() - delta < 0) //if going off-screen
            {
                super.setX(0);
            }
            else
            {
                super.setX(super.getX() - delta);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            if(super.getX() + getWidth() < Gdx.graphics.getWidth())
            {
                super.setX(super.getX() + delta);
            }
            else
            {
                super.setX(Gdx.graphics.getWidth() - getWidth());
            }
        }
        update();
    }
    public void collide(Entity e, Entity e2)
    {

    }
    public void dispose()
    {
        shape.dispose();
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
