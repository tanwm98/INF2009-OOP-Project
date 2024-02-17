package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class AI extends Entity implements IMovement {

    public void update(Entity entity){

    }
    @Override
    public void render(){
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(super.getX(),super.getY(),50);
        shape.end();
    }
    @Override
    public void movement(){

    }
}
