package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.s31b.castleoffense.player.Player;
import java.awt.image.BufferedImage;

/**
 *
 * @author GoosLaptop
 */
public class Offensive extends Entity {
    
    private final int hitpoints;
    private final int movementSpeed;
    private final int killReward;
    
    public Offensive(String name, String descr, BufferedImage sprite, Player owner, float price, int hp, int speed, int reward) {
        super(name, descr, sprite, price, owner);
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getKillReward() {
        return killReward;
    }

    @Override
    public boolean Buy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrice(float price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void Update(){
        
    }
}
