package com.mygdx.game;

import java.util.*;
import com.badlogic.gdx.graphics.Color;

public class EntityManager{
    private List<Entity> entityList;

    public EntityManager(){
        entityList = new ArrayList<>();

    }

    public void createEntity(Entity entity) {
        entityList.add(entity);
    }
    public void removeEntity(Entity entity) {
    	entityList.remove(entity);
    }


}
