package com.s31b.castleoffense.player;

import com.s31b.castleoffense.game.entity.*;

/**
 *
 * @author Goos
 */
public class Player {
    private final int id;
    private String name;
    private int points;
    private int gold;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        initPlayer();
    }
    
    private void initPlayer() {
        points = 0;
        gold = 100;
    }
    
    /**
     * Removes currency from buying player
     * @return true if payment was made, otherwise false
     */
    /*public boolean BuyEntity(EntityType type) {
        
    }*/
    
    public void addPoints(int ammount) {
        points += ammount;
    }
    
    public void addGold(int ammount) {
        gold += ammount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public int getGold() {
        return gold;
    }
    
}
