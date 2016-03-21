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
    
    private final Tile tile;
    
    public Defensive(EntityType type, String name, String descr, BufferedImage sprite, Player owner, float price, int dps, int range, Tile tile) {
        super(type, name, descr, sprite, price, owner);
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
}
