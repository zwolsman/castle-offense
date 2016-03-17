package com.s31b.logic.classes;

import java.awt.image.BufferedImage;

/**
 *
 * @author Goos
 */
public class Castle {
    private int hitpoints;
    private BufferedImage sprite;
    
    private Player owner;

    public Castle(int hp, BufferedImage sprite, Player owner) {
        this.hitpoints = hp;
        this.sprite = sprite;
        this.owner = owner;
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

    public BufferedImage getSprite() {
        return sprite;
    }

    public Player getOwner() {
        return owner;
    }
    
    
}
