package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Castle;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * An attacking unit
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

    /**
     * Initialize a new offensive object
     *
     * @param type The type of the entity.
     * @param name The name of the entity
     * @param descr The description of the name
     * @param sprite The Texture for the NPC
     * @param owner The player who bought it so it will not attack own units and
     * turrets won't kill it
     * @param price The price the player bought paid for it
     * @param hp The hp (Hitpoints) of the unit
     * @param speed The speed of the unit. Must be positive
     * @param reward The reward the player gets in gold
     */
    public Offensive(EntityType type, String name, String descr, String sprite, Player owner, int price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price, owner);
        hitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
        destinationCastle = determineCastle(owner);
    }

    public Offensive(OffensiveDAO data, Player owner) {
        super(EntityType.getTypeFromString(data.getType()), data.getName(), data.getDescr(), data.getSprite(), data.getPrice(), owner);
        hitpoints = data.getHP();
        movementSpeed = data.getSpeed();
        killReward = data.getReward();
        destinationCastle = determineCastle(owner);
    }

    private Castle determineCastle(Player owner) {
        if (owner.getId() == 1) {
            return owner.getGame().getPlayerById(2).getCastle();
        } else {
            return owner.getGame().getPlayerById(1).getCastle();
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

    /**
     * Calculates the next available position to move to. Will return null if
     * there will not be one
     *
     * @return A tile if it found one, null if it did not
     */
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

    /**
     * Calculates the next position to move to. Will use the speed of the unit
     * for movement
     */
    public boolean update() {
        //Tile temp = getNextPostition();
        //currentTile = getNextPostition();
        Tile tempTile = getNextPosition();
        if (this.hitpoints <= 0) {
            return false;
        }

        if (tempTile == null) {
            // TODO
            // damage enemy castle
            // remove this offensive entity from the game/wave
            return false;
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
        return true;
    }

    public void draw(SpriteBatch batch) {
        if (!isSpawned()) {
            return;
        }
        //TODO make this dynamic
        Texture t = TextureFactory.getTexture("zoimbie1_hold_" + direction.toString().toLowerCase());
        batch.draw(t, ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
    }

    public boolean isSpawned() {
        return currentTile != null;
    }

    /**
     * Spawn the unit on the offensive spawn position
     */
    public void spawn() {
        this.currentTile = owner.getOffensiveSpawnPosition();

        //System.out.println("Spawned!");
        ingameX = currentTile.getX() * Globals.TILE_WIDTH;
        ingameY = currentTile.getY() * Globals.TILE_HEIGHT;
    }
}

/**
 * The direction a unit can move to.
 *
 * @author fhict
 */
enum Direction {
    Up,
    Down,
    Left,
    Right
}
