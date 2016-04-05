package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Castle;
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
    //private final Castle destinationCastle;

    private Tile currentTile;

    public Offensive(EntityType type, String name, String descr, Texture sprite, Player owner, float price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price, owner);
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
        this.currentTile = owner.getOffensiveSpawnPosition();
        path = generatePath();
//        if (owner.getId() == 1) {
//            destinationCastle = 
//        }
    }
    
    private ArrayList<Tile> generatePath() {
        ArrayList<Tile> resultSet = new ArrayList<Tile>();
        List<Tile> walkables = owner.getGame().getMap().getAllWalkableTiles();
        
        Tile tempCurrTile = currentTile;
        
        // set the spawntile
        resultSet.add(tempCurrTile);
        walkables.remove(tempCurrTile);
        
        Tile tempTile = null;
        // add the next tile to the path until there are no tiles left
        while (walkables.size() > 0) {
            tempTile = null;
            for (Tile t : walkables) {
                if (Math.abs(Math.abs(t.getX() - tempCurrTile.getX()) - Math.abs(t.getY() - tempCurrTile.getY())) == 1) {
                    resultSet.add(t);
                    tempTile = t;
                    tempCurrTile = t;
                }
            }
            if(tempTile != null){
                // remove the last found tile from the walkables list
                walkables.remove(tempTile);
                System.out.println(walkables.size());
            }
            
        }
        return resultSet;
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
        try {
            return path.get(path.indexOf(currentTile) + 1);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void removeHealth(int amount) {
        hitpoints -= amount;
    }

    public void update() {
        currentTile = getNextPostition();
        if (currentTile != null) {
            // move the offensive entity to the next tile
            
            // TODO: Marvin
            
        } else {
            // the entity has reached an enemy castle
            
        }
    }
    
    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(super.getSprite(), currentTile.getX(), currentTile.getY());
        batch.end();
    }
}
