package com.mygdx.game.Managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.Asteroid;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Planet;
import com.mygdx.game.Entity.Satellite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;


public class CollisionManager{

	private Rectangle object1,object3;
	private float planetx , planety , planetrad;
	private Circle object2;
	private boolean collide;
    private List<Entity> CollideableObjects;
	
	public CollisionManager(){
        CollideableObjects = new ArrayList<>();
	}
	
    public void addEntity(Entity entity) {
        if(entity.isCollideable()) // If the entity is AI controlled, add it to the list
        {
                CollideableObjects.add(entity);
        }
    }
    
    public List<Entity> getCollideables(){
    	return CollideableObjects;
    }

	public boolean detectCollision(Entity e1 , Entity e2) {
		collide = false;
		object1 = new Rectangle(e1.getX() , e1.getY() , e1.getWidth() , e1.getHeight());
		if(e2 instanceof Asteroid || e2 instanceof Satellite) {
			//More accurate collision for asteroid instead of using circle
			object3 = new Rectangle(e2.getX() , e2.getY() , e2.getWidth() , e2.getHeight());
			if(Intersector.overlaps(object1 , object3) == true) {
				collide = true;
			}
		}
		else {
			planetx = e2.getX()+e2.getWidth()/2;
			planety = e2.getY()+e2.getHeight()/2;
			planetrad = e2.getWidth()/2;
			
			object2 = new Circle(planetx , planety, planetrad);
	
			if(Intersector.overlaps(object2 , object1) == true) {
				collide = true;
			}
		}
		return collide;
			
	}
}
