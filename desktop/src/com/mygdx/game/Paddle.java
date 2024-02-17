package com.mygdx.game;

public class Paddle extends Entity {
    public Paddle(){
        super.setXSpeed(10);
        super.setYSpeed(0);
        super.setX(120);
        super.setY(20);
        super.setControl(false);
    }
    @Override
    public void update(Entity entity) {

    }
    public void render(){

    }
}
