package com.s31b.castleoffense.player;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Goos
 */
public class Castle {
    private int hitpoints;
    private Texture sprite;    
    private Player owner;

    public Castle(int hp, Texture sprite, Player owner) {
        this.hitpoints = hp;
        this.sprite = sprite;
        this.owner = owner;
        initCastle();
    }
    
    private void initCastle() {
        hitpoints = 10;
    }
    
    public void lostHitpoints(int ammount) {
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
