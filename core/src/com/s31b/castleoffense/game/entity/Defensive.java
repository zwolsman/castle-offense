package com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.map.Tile;
import java.awt.image.BufferedImage;

/**
 *
 * @author Goos
 */
public class Defensive extends Entity {
    
    private final int damagePerSecond;
    private final int range;
    
    private Tile tile;

    public Defensive(String name, String descr, BufferedImage sprite, Player owner, float price, int dps, int range, Tile tile) {
        super(name, descr, sprite, price, owner);
        damagePerSecond = dps;
        this.range = range;
        this.tile = tile;
    }

    public int getDamagePerSecond() {
        return damagePerSecond;
    }

    public int getRange() {
        return range;
    }
    
    public Tile getPosition() {
        return this.tile;
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
    
}
