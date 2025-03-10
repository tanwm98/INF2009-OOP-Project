package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Managers.OutputManager;


public class Satellite extends Entity {

	private Texture tex;

	private SpriteBatch batch = new SpriteBatch();

	private Player player;
	private MyGdxGame game;

	public Satellite(int texSelect) {
		super();
		SatelliteTextureFactory satFactory = new SatelliteTextureFactory();
		setTexture(satFactory.makeSatellite(texSelect));
	}

	public Satellite(float posX, float posY, float speedX, float speedY, boolean Collideable, MyGdxGame game, Player player) {
		super.setX(posX);
		super.setY(posY);
		super.setXSpeed(speedX);
		super.setYSpeed(speedY);
		super.setCollideable(Collideable);
		// Randomly pick a satellite texture.
		SatelliteTextureFactory satFactory = new SatelliteTextureFactory();
		int texSelect = MathUtils.random(1, 2);
		setTexture(satFactory.makeSatellite(texSelect));
		super.setHeight(this.getTexture().getHeight());
		super.setWidth(this.getTexture().getWidth());
		this.player = player;
		this.game = game;
	}

	public Texture getTexture() {
		return tex;
	}

	public void setTexture(Texture t) {
		tex = t;
	}

	public void update(float delta)
	{
		super.update(delta);
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
		OutputManager.getInstance().draw(batch, tex, x, y, originX, originY, texWidth, texHeight, 1, rotate);
		batch.end();
	}
	@Override
	public void collide(boolean collide) {
		if (collide && super.getCollisionCD() <= 0) {
			game.getPlayer().decreaseLives(1);
			OutputManager.getInstance().playsound("Music/sfx/hit_sfx.wav");
			super.setCollisionCD(super.getCD_period());
		}
	}
	@Override
	public void move() {
		float delta = Gdx.graphics.getDeltaTime();
		float moveSpeed = getXSpeed() * delta;
        float rotateSpeed = MathUtils.random(1, 7);
		super.setX(super.getX() - moveSpeed);
		super.setRotation(super.getRotation() + rotateSpeed);
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}

}
