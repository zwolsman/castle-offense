package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.s31b.castleoffense.player.Player;

/**
 *
 * @author Goos
 */
public class Defensive extends Entity {
    
    private final int damagePerSecond;
    private final int range;
    
    public Defensive(EntityType type, String name, String descr, Texture sprite, Player owner, float price, int dps, int range) {
        super(type, name, descr, sprite, price, owner);
        damagePerSecond = dps;
        this.range = range;
    }

    public int getDamagePerSecond() {
        return damagePerSecond;
    }
}
