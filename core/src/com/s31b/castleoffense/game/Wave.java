package com.s31b.castleoffense.game;

import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.game.entity.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goos
 */
public class Wave {
    private int number;
    
    private boolean player1done;
    private boolean player2done;
    
    private List<Entity> entities;
    
    public Wave(int number) {
        this.number = number;
        initWave();
    }
    
    private void initWave() {
        entities = new ArrayList<Entity>();
        player1done = false;
        player2done = false;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void addOffensive(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * Adds a defensive entity to the current wave
     * @param entity entity to be added, if entity isn't defensive this could go wrong.
     * @param tile tile that entity is placed on
     */
    public void addDefensive(Entity entity, Tile tile) {
        entities.add(entity);
    }
    
    public void endWave(int playerId) {
        switch (playerId) {
            case 1:
                player1done = true;
                break;
            case 2:
                player2done = true;
                break;
            default:
                break;
        }
        
        if (player1done && player2done) {
            display();
        }
    }
    
    public void display() {
        throw new UnsupportedOperationException();
    }
}
