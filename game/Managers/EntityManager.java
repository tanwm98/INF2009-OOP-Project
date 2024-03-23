	package com.mygdx.game.Managers;

import java.util.*;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Entity.Asteroid;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Planet;
import com.mygdx.game.Managers.CollisionManager;
import com.mygdx.game.Managers.AIControlManager;

public class EntityManager extends MyGdxGame{
    private List<Entity> entityList;
    private List<Entity> CollideableObjects;
    private CollisionManager collisionManager;
    private AIControlManager aiControlManager;
    private boolean collide;

    public EntityManager(){
        entityList = new ArrayList<>();
        aiControlManager = new AIControlManager();
		collisionManager = new CollisionManager();

    }
    public EntityManager(AIControlManager aiControlManager, CollisionManager collisionManager)
    //uses the same AIControlManager as the game
    {
        this.entityList = new ArrayList<>();
        this.aiControlManager = aiControlManager;
        this.collisionManager = collisionManager;
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
        if (entity.isAIControl()) {
            aiControlManager.addEntity(entity); //adds entity with AI movement to the list
        }
        if(entity.isCollideable()) {
        	collisionManager.addEntity(entity);
        }
    }

    public void renderEntities() {
        for (Entity entity : entityList) {
            entity.render();
        }
    }
    public void removeEntity(Entity entity) {
    	entityList.remove(entity);
    }

    public void dispose() {
    	for (Entity entity : entityList) {
    		entity.dispose();
    	}
    }

	public void detect() {
        if (entityList == null) {
            throw new NullPointerException("Entity list is null");
        }
        if (entityList.isEmpty()) {
            throw new IndexOutOfBoundsException("Entity list is empty");
        }
        else {
        	CollideableObjects = collisionManager.getCollideables();
        	Entity player = CollideableObjects.get(0);
        	for(int i = 1; i<CollideableObjects.size(); i++){
        		Entity object = CollideableObjects.get(i);
        		collide = collisionManager.detectCollision(player, object);
        		if(collide) {
        			if(object instanceof Asteroid) {
        				player.collide(collide);
        			}
        			object.collide(collide);
        		}
        		else {
        			player.collide(collide);
        		}
        	}
        }
	}

}