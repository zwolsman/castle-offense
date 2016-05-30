package com.s31b.castleoffense.player;

import com.badlogic.gdx.graphics.Texture;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureGlobals;
import java.util.Objects;

/**
 * The castle of a player
 *
 * @author Goos
 */
public class Castle {

    private int hitpoints;
    private final Player owner;

    public Castle(Player owner) {
        this.hitpoints = Globals.CASTLE_HP;
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
        return TextureGlobals.CASTLE_SPRITE;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Castle)) {
            return false;
        }
        Castle c = (Castle) other;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.hitpoints;
        hash = 13 * hash + Objects.hashCode(this.owner);
        return hash;
    }
}
