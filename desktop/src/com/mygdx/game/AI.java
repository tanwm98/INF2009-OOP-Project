package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class AI extends Entity implements IMovement {

    public void update(Entity entity){

    }
    @Override
    public void render(){
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(super.getX(),super.getY(),50);
        shape.end();
    }
    public void update()
    {

    }
    public void collide(Entity e1, Entity e2)
    {

    }
    @Override
    public void move(){

    }
	@Override
	public void collide(boolean collide) {
		
		
	}
}
