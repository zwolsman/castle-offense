package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;

/**
 *
 * @author Goos
 */
public class Defensive extends Entity {
    
    private final int damagePerSecond;
    private final int range;
    
    private Tile position;
    
    public Defensive(EntityType type, String name, String descr, Texture sprite, Player owner, float price, int dps, int range) {
        super(type, name, descr, sprite, price, owner);
        damagePerSecond = dps;
        this.range = range;
        position = new Tile(0, 0);
    }
    
    public void setPosition(Tile position) {
        this.position = position;
    }

    public int getDamagePerSecond() {
        return damagePerSecond;
    }
    
    public void update() {
        
    }
    
    public void draw() {
        Globals.SPRITE_BATCH.draw(super.getSprite(), position.getX(), position.getY(), Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
    }
}
