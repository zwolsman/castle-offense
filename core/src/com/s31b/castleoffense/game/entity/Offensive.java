package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import static com.s31b.castleoffense.game.Clock.Delta;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GoosLaptop
 */
public class Offensive extends Entity {

    private int hitpoints;
    private final int movementSpeed;
    private final int killReward;
    private final ArrayList<Tile> path;

    private Tile currentTile;

    public Offensive(EntityType type, String name, String descr, Texture sprite, Player owner, float price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price, owner);
        List<Tile> walkables = owner.getGame().getMap().getAllWalkableTiles();
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
        this.currentTile = owner.getOffensiveSpawnPosition();
        path = new ArrayList<Tile>();
        path.add(currentTile);
        walkables.remove(currentTile);        
        while (walkables.size() > 0) {
            Tile tempTile = null;
            for (Tile t : walkables) {
                if (Math.abs(Math.abs(t.getX() - currentTile.getX()) - Math.abs(t.getY() - currentTile.getY())) == 1) {
                    path.add(t);
                    //walkables.remove(t);
                    tempTile = t;
                    currentTile = t;
                }
            }
            if(tempTile != null){
                
                walkables.remove(tempTile);
                System.out.println(walkables.size());
            }
            
        }
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

    public Tile getPosition() {
        return this.currentTile;
    }

    public Tile getNextPostition() {
        return path.get(path.indexOf(currentTile) + 1);
    }

    public void SetNextPosition() {
        currentTile = path.get(path.indexOf(currentTile) + 1);
    }

    public void removeHealth(int amount) {
        hitpoints -= amount;
    }

    public void update() {
        currentTile = getNextPostition();
    }
    
    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(super.getSprite(), currentTile.getX(), currentTile.getY());
        batch.end();
    }
}
