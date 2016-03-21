package com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;
import java.awt.image.BufferedImage;

/**
 *
 * @author GoosLaptop
 */
public class Offensive extends Entity {

    private int hitpoints;
    private final int movementSpeed;
    private final int killReward;
    
    private Tile spawnPosition;
    
    public Offensive(EntityType type, String name, String descr, BufferedImage sprite, Player owner, float price, int hp, int speed, int reward, Tile spawnPosition) {
        super(type, name, descr, sprite, price, owner);
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
        this.spawnPosition = spawnPosition;
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
    
    public void removeHealth(int ammount) {
        hitpoints -= ammount;
    }
}
