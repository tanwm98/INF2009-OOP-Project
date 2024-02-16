package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Entity {

    public Circle(int posX, int posY, int radius, ShapeRenderer shape)
    {
        super(posX, posY, radius, shape);
    }
    shape.begin(ShapeRenderer.ShapeType.Filled);
    shape.circle(50,50,50);
    shape.end();
}
