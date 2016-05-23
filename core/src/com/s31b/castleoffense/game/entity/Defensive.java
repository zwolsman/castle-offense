package com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;

/**
 * The defensive class i used for objects that defends a player. Eg a tower
 *
 * @author Goos
 */
public class Defensive extends Entity {

    private int damagePerSecond;
    private int range;

    private Tile position;

    public Defensive(EntityType type, String name, String descr, String sprite, Player owner, int price, int dps, int range) {
        super(type, name, descr, sprite, price, owner);
        damagePerSecond = dps;
        this.range = range;
        position = new Tile(0, 0);
    }

    public Defensive(DefensiveDAO data, Player owner) {
        super(EntityType.getTypeFromString(data.getType()), data.getName(), data.getDescr(), data.getSprite(), data.getPrice(), owner);
        damagePerSecond = data.getDPS();
        this.range = data.getRange();
        position = new Tile(0, 0);
    }

    /**
     * Update the position of the defensive object if needed.
     *
     * @param position
     */
    public void setPosition(Tile position) {
        this.position = position;
    }

    /**
     * Damage per second (DPS) of the object
     *
     * @return
     */
    public int getDamagePerSecond() {
        return damagePerSecond;
    }

    /**
     * @param damagePerSecond the damagePerSecond to set
     */
    public void setDamagePerSecond(int damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    public void update() {

    }

    public void draw() {
        TextureGlobals.SPRITE_BATCH.draw(
                TextureFactory.getTexture(super.getSprite()),
                position.getX() * Globals.TILE_WIDTH,
                position.getY() * Globals.TILE_HEIGHT,
                Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }
}
