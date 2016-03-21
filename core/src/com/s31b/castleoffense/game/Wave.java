package com.s31b.castleoffense.game;

import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;
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
    
    private CoGame game;
    
    public Wave(int number, CoGame game) {
        this.number = number;
        this.game = game;
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
    
    public void addEntity(Entity entity) {
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
        // display current wave
        for (Entity entity : entities) {
            if (entity instanceof Offensive) {
                // spawn entity one by one from spawnposition after last offensive has been spawned
            }
            else if (entity instanceof Defensive) {
                // spawn entity on its position
            }
        }
        
        // after displaying start a new wave
        game.nextWave();
    }
}
