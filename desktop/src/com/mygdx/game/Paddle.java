package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Paddle extends Entity {
    private Color color = Color.WHITE;
    public Paddle(){
        super.setXSpeed(0);
        super.setYSpeed(0);
        super.setX(0);
        super.setY(0);
        super.setControl(false);
    }
    public Paddle(int x, int y, int xSpeed)
    {
        super.setX(x);
        super.setY(y);
        super.setXSpeed(xSpeed);
    }
    @Override
    public void update() {

    }
    public void render(){
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(super.getX(),super.getY(),100,20);
        shape.setColor(color);
        shape.end();
    }

    public void collide(Entity e, Entity e2)
    {

    }
}
