package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.Clock;
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
//    private final ArrayList<Tile> path;
    private final Castle destinationCastle;

    private final int[][] corners = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private List<Tile> usedTiles = new ArrayList<Tile>();
    private int ingameX, ingameY;

    private Tile currentTile = null;

    public Offensive(EntityType type, String name, String descr, Texture sprite, Player owner, float price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price, owner);
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;

        if (owner.getId() == 1) {
            destinationCastle = owner.getGame().getPlayerById(2).getCastle();
        } else {
            destinationCastle = owner.getGame().getPlayerById(1).getCastle();
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

    public Tile getNextPosition() {
        Tile[][] walkableTiles = owner.getGame().getMap().getAllWalkableTiles2D();
        for (int[] corner : corners) {
            int curX = currentTile.getX() + corner[0];
            int curY = currentTile.getY() + corner[1];

            if (curX < 0 || curY < 0) {
                continue;
            }
            if (walkableTiles[curX][curY] == null) {
                continue;
            }
            if (usedTiles.contains(walkableTiles[curX][curY])) {
                continue;
            }
            return walkableTiles[curX][curY];
        }
        return null;
    }

    public void removeHealth(int amount) {
        hitpoints -= amount;
    }

    public void update() {
        // TODO: implement movementspeed

        //Tile temp = getNextPostition();
        //currentTile = getNextPostition();
        Tile tempTile = getNextPosition();
        if (tempTile == null) {
            //System.out.println("I AM EMPTY");
            return;
        }
        float distance = Gdx.graphics.getDeltaTime() * movementSpeed;
//        System.out.println("movement distance: " + Double.toString(distance));
//        System.out.println(tempTile.toString());
//        System.out.println(currentTile.toString());
        if (tempTile.getX() == currentTile.getX()) { //Ik beweeg verticaal

            if (currentTile.getY() > tempTile.getY()) //Naar beneden
            {
                distance = 0 - distance;
            }
            if (currentTile.getY() < tempTile.getY()) { //Naar boven
                distance = distance;
            }

            if (!currentTile.contains(ingameX, ingameY + (int) distance)) {
                usedTiles.add(currentTile);
                currentTile = tempTile;

            }
            ingameX = currentTile.getX() * Globals.TILE_WIDTH;
            ingameY += distance;
        } else if (tempTile.getY() == currentTile.getY()) {
            if (currentTile.getX() < tempTile.getX()) { //Naar rechts
                distance = distance;
            }
            if (currentTile.getX() > tempTile.getX()) { //Naar links
                distance = 0 - distance;
            }

            if (!currentTile.contains(ingameX + (int) distance, ingameY)) {
                usedTiles.add(currentTile);
                currentTile = tempTile;

            }
            ingameY = currentTile.getY() * Globals.TILE_HEIGHT;
            ingameX += distance;
        }
    }

    public void draw(SpriteBatch batch) {
        if (!isSpawned()) {
            return;
        }

        //System.out.println("Drawing at " + currentTile.toString());
        //System.out.println("ingameX: " + Integer.toString(ingameX) + " IngameY: " + Integer.toString(ingameY));

        batch.draw(super.getSprite(), ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
    }

    public boolean isSpawned() {
        return currentTile != null;
    }

    public void spawn() {
        this.currentTile = owner.getOffensiveSpawnPosition();

        //System.out.println("Spawned!");

        ingameX = currentTile.getX() * Globals.TILE_WIDTH;
        ingameY = currentTile.getY() * Globals.TILE_HEIGHT;
    }
}
