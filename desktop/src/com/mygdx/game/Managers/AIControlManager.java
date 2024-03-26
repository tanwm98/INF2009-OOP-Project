package com.mygdx.game.Managers;


import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Interfaces.IMovement;

import java.util.ArrayList;
import java.util.List;

public class AIControlManager {
    private List<IMovement> aiMovements;
    public AIControlManager()
    {
        aiMovements = new ArrayList<>();
    }
    public void addEntity(Entity entity) {
                aiMovements.add(entity);
    }
    public void moveEntities()
    {
        for (IMovement entity : aiMovements) {
            entity.move();
        }
    }
}
