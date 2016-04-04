package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import static com.s31b.castleoffense.game.Clock.Delta;
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
    private Vector2 currentPosition;
    private boolean first = true;
    
    public Offensive(EntityType type, String name, String descr, Texture sprite, Player owner, float price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price, owner);
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
        currentPosition = owner.getOffensiveSpawnPosition();
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
    
    public void update(){
        if(first){
            first = false;
        }
        else{
            currentPosition.x += Delta() * movementSpeed;
        }
    }
}
