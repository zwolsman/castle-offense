package com.s31b.castleoffense.player;

import com.badlogic.gdx.graphics.Texture;
import com.s31b.castleoffense.Globals;

/**
 * The castle of a player
 *
 * @author Goos
 */
public class Castle {

    private int hitpoints;
    private Texture sprite;
    private Player owner;

    public Castle(Player owner) {
        this.hitpoints = Globals.CASTLE_HP;
        this.sprite = Globals.CASTLE_SPRITE;
        this.owner = owner;
        initCastle();
    }

    /**
     * Set the hitpoints of a castle to the initial value (10)
     */
    private void initCastle() {
        hitpoints = 10;
    }

    public void loseHitpoints(int ammount) {
        hitpoints -= ammount;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public Texture getSprite() {
        return sprite;
    }

    public Player getOwner() {
        return owner;
    }
}
