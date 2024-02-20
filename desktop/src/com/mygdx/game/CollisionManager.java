package com.mygdx.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager{

	private Rectangle object_rect;
	private Circle ball_rect;
	private boolean collide;

	public boolean detectCollision(Entity e1 , Entity e2) {
		ball_rect = new Circle(e1.getX() , e1.getY() , 10);
		object_rect = new Rectangle(e2.getX() , e2.getY() , e2.getWidth() , e2.getHeight());
		if(Intersector.overlaps(ball_rect , object_rect) == true) {
			collide = true;
		}
		else {
			collide = false;
		}
		return collide;
			
	}
}
