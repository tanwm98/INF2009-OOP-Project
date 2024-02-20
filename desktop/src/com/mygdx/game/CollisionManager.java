package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager{

	private Rectangle player_rect;
	private Circle object_rect;
	private boolean collide;

	public boolean detectCollision(List<Entity> entitylist) {
			Entity player = entitylist.get(0);
			Entity ball = entitylist.get(1);
			player_rect = new Rectangle(player.getX() , player.getY() , player.getWidth() , player.getHeight());
			object_rect = new Circle(ball.getX() , ball.getY() , ball.getHeight());

			
			if(Intersector.overlaps(object_rect , player_rect)) {
				collide = true;
			}
			return collide;
			
	}
}
