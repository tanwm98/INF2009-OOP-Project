package com.mygdx.game.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Managers.OutputManager;

public class Block extends Entity{

    ShapeRenderer shape = new ShapeRenderer();
	OutputManager outputManager= new OutputManager();
	
    public Block(float x, float y, float width, float height, Color color)
    {
        super.setX(x);
        super.setY(y);
        super.setWidth(width);
        super.setHeight(height);
        super.setColor(color);
    }
    public void update()
    {

    }

    public void render()
    {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(super.getColor());
        outputManager.draw(shape,super.getX(), super.getY(), super.getWidth(), super.getHeight());
        shape.end();
    }


    public void dispose()
    {
        shape.dispose();
    }

    public void move()
    {

    }
    	public void collide(boolean collide) {
            
	}
}