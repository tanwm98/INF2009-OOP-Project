package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class Satellite extends Entity{
	
	private Texture tex;
	
	SpriteBatch batch = new SpriteBatch();
	
    public Satellite() {

    }
	
    public Satellite(String filePath) {
    	super();
		tex = new Texture(Gdx.files.internal(filePath));
    }
    
    
	public Satellite(float posX, float posY,float speedX, float speedY,boolean aiFlag,boolean Collideable)
	{
		super.setX(posX);
		super.setY(posY);
		super.setXSpeed(speedX);
		super.setYSpeed(speedY);
		super.setControl(aiFlag);
		super.setCollideable(Collideable);

		
		// Randomly pick a satellite texture.
		SatelliteTextureFactory satFactory = new SatelliteTextureFactory();
        int texSelect = MathUtils.random(0, 3);
		setTexture(satFactory.makeSatellite(texSelect));
		super.setHeight(this.getTexture().getHeight());
		super.setWidth(this.getTexture().getWidth());
	}
    
	public Texture getTexture() {
		return tex;
	}
	
	public void setTexture(Texture t) {
		tex = t;
	}
	
	@Override
	public void collide(boolean collide) {
		
	}

	@Override
	public void move() {
		float delta = Gdx.graphics.getDeltaTime();
		float moveSpeed = getXSpeed() * delta;
        float rotateSpeed = MathUtils.random(2, 5);
		setX(getX() - moveSpeed);
		setRotation(getRotation() + rotateSpeed);
	}


	@Override
	public void render() {
		tex = this.getTexture();
		float x = this.getX();
		float y = this.getY();
		float texWidth = tex.getWidth();
		float texHeight = tex.getHeight();
		float originX = texWidth/2;
		float originY = texHeight/2;
		float rotate = this.getRotation();
		batch.begin();
		getoutputManager().draw(batch, tex, x, y, originX, originY, texWidth, texHeight, 1, rotate);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}

}
