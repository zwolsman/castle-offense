package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
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
    private Direction direction = Direction.Right;

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
                direction = Direction.Down;

            }
            if (currentTile.getY() < tempTile.getY()) { //Naar boven
                distance = distance;
                direction = Direction.Up;
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
                direction = Direction.Right;
            }
            if (currentTile.getX() > tempTile.getX()) { //Naar links
                distance = 0 - distance;
                direction = Direction.Left;
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
//TODO make this dynamic
        Texture t = TextureFactory.getTexture("zoimbie1_hold_" + direction.toString().toLowerCase());
        batch.draw(t, ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
//
//        String t = "zoimbie1_hold_right";
//        switch (direction) {
//            case Down:
//                t = "zoimbie1_hold_down";
//                break;
//            case Up:
//                t = "zoimbie1_hold_up";
//                break;
//            case Left:
//                t = "zoimbie1_hold_left";
//                break;
//            case Right:
//                //
//                break;
//        }
//        TextureRegion region = new TextureRegion(TextureFactory.getTexture(t), ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
//        System.out.println(direction);
//        batch.draw(region, ingameX, ingameY);
        // batch.draw(region, ingameX, ingameY, region.getRegionWidth() / 2.0f, region.getRegionHeight() / 2.0f, region.getRegionWidth(), region.getRegionHeight(), 1f, 1f, 90.0f, true);

//        switch (direction) {
//            case Down:
//                //draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation)
//                // batch.draw(region, (float)ingameX, (float)ingameY, (float)Globals.TILE_WIDTH, (float)Globals.TILE_HEIGHT, 1.0f,1.0f, 90.0f);
//                //batch.draw(region, inagemX, (float)ingameY, (float)Globals.TILE_WIDTH, (float)Globals.TILE_HEIGHT, (float)Globals.TILE_WIDTH, Gl, price, price, price, true);
//
//                batch.draw(region, ingameX, ingameY, (Gdx.graphics.getWidth() - region.getRegionWidth()) / 2.0f, (Gdx.graphics.getHeight() - region.getRegionHeight()) / 2.0f, region.getRegionWidth(), region.getRegionHeight(), 1f, 1f, 90.0f, true);
//
//                //batch.draw(sprite,(Gdx.graphics.getWidth() - sprite.getRegionWidth()) / 2.0f,(Gdx.graphics.getHeight() - sprite.getRegionHeight()) / 2.0f,sprite.getRegionWidth()/2.0f,sprite.getRegionHeight()/2.0f, sprite.getRegionWidth(), sprite.getRegionHeight(), 1f, 1f,count, false);
//                region.flip(false, true);
//                break;
//            case Up:
//                batch.draw(region, ingameX, ingameY, region.getRegionWidth() / 2.0f, region.getRegionHeight() / 2.0f, region.getRegionWidth(), region.getRegionHeight(), 1f, 1f, 270.0f, true);
//            case Right:
//                batch.draw(super.getSprite(), ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
//            default:
//                //batch.draw(super.getSprite(), ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
//                break;
//        }
        //batch.draw(super.getSprite(), ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
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

enum Direction {
    Up,
    Down,
    Left,
    Right
}
