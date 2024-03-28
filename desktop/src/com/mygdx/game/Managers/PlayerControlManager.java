package com.mygdx.game.Managers;

import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Interfaces.IMovement;
import com.mygdx.game.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerControlManager {
    private List<IMovement> playerMovements;
    public PlayerControlManager()
    {
        playerMovements = new ArrayList<>();
    }
    public void addEntity(Entity entity) {
            playerMovements.add(entity);
    }

    public void moveEntities()
    {
        for (IMovement entity : playerMovements) {
            entity.move();
        }
    }
}
