package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.InputManager;
import com.mygdx.game.OutputManager;

public class Paddle extends Entity {
    private Color color = Color.WHITE;
    
    ShapeRenderer shape = new ShapeRenderer();
    InputManager inputManager = new InputManager();
    OutputManager outputManager = new OutputManager();
    
    public Paddle(){
        super.setXSpeed(0);
        super.setYSpeed(0);
        super.setX(0);
        super.setY(0);
        super.setControl(false);
    }
    public Paddle(float x, float y, float xSpeed,float width, float height,Color color,boolean aiFlag)
    {
        super(x,y,xSpeed,0,width,height,color,aiFlag);
    }
    @Override
    public void update() {
        System.out.printf("X: %f\n",super.getX());
    }
    @Override
    public void render(){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(color);
        shape.rect(super.getX(),super.getY(),super.getWidth(),getHeight());
        //outputManager.draw(shape,super.getX(), super.getY(), super.getWidth(), super.getHeight());
        shape.end();
    }

    public void move()
    {
        float delta = getXSpeed()*Gdx.graphics.getDeltaTime();
        if(inputManager.isLeftKeyPressed())
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
        if(inputManager.isRightKeyPressed())
        {
            if(super.getX() + super.getWidth() < Gdx.graphics.getWidth())
            {
                super.setX(super.getX() + delta);
            }
            else
            {
                super.setX(Gdx.graphics.getWidth() - super.getWidth());
            }
        }
    }
    /*{
    	InputManager input=new InputManager();
    	input.keyboard(this);
       update();
    }*/

    public void collide(boolean collide)
    {

    }
    public void dispose()
    {
        shape.dispose();
    }
}