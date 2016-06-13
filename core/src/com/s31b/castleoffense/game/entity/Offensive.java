package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.s31b.castleoffense.AudioFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.Settings;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.TextureGlobals;
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

    private float hitpoints;
    private float totalHitpoints;
    private final int movementSpeed;
    private final int killReward;
    private final Castle destinationCastle;
    private final int[][] corners = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private List<Tile> usedTiles = new ArrayList<>();
    private int ingameX, ingameY;
    private Direction direction = Direction.Right;
    private Sound deathSound;

    private Tile currentTile = null;

    /**
     * Initialize a new offensive object for UNIT TEST
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
    public Offensive(EntityType type, String name, String descr, String sprite, Player owner, int price, float hp, int speed, int reward) {
        super(type, name, descr, sprite, price, owner);
        hitpoints = hp;
        totalHitpoints = hp;
        movementSpeed = speed;
        killReward = reward;
        destinationCastle = getEnemyCastle(owner);
    }

    /**
     * Constructor for general use (From database)
     *
     * @param data
     * @param owner
     */
    public Offensive(OffensiveDAO data, Player owner) {
        super(EntityType.getTypeFromString(data.getType()), data.getName(), data.getDescr(), data.getSprite(), data.getPrice(), owner);
        hitpoints = data.getHealthPoints();
        totalHitpoints = data.getHealthPoints();
        movementSpeed = data.getSpeed();
        killReward = data.getReward();
        destinationCastle = getEnemyCastle(owner);
        deathSound = AudioFactory.getSound("death.wav");
    }

    /**
     * Returns the castle of the enemy if there is one, otherwise null.
     *
     * @param owner The owner of the offensive object
     * @return
     */
    private Castle getEnemyCastle(Player owner) {

        if (owner.getGame().getPlayers().size() <= 1) {
            return null;
        }
        return owner.getGame().getPlayerById((owner.getId() + 1) % owner.getGame().getPlayers().size()).getCastle();
    }

    public double getHitpoints() {
        return hitpoints;
    }

    public float getTotalHitpoints() {
        return totalHitpoints;
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

            if (curX < 0 || curY < 0 || curX >= Globals.TILES_X || curY >= Globals.TILES_Y) {
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

    public void removeHealth(double amount) {
        hitpoints -= amount;
    }

    public boolean isDead() {
        if (this.hitpoints <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the next position to move to. Will use the speed of the unit
     * for movement
     */
    public String update() {
        Tile tempTile = getNextPosition();

        if (isDead()) {
            return "Dead";
        }

        if (tempTile == null) {
            // TODO
            // damage enemy castle
            // remove this offensive entity from the game/wave
            return "Null";
        }

        float distance = Gdx.graphics.getDeltaTime() * movementSpeed;
        Vector2 centerNextTile = new Vector2(tempTile.getX(true) + Globals.TILE_WIDTH / 2, tempTile.getY(true) + Globals.TILE_HEIGHT / 2);
        Vector2 centerTile = new Vector2(currentTile.getX(true) + Globals.TILE_WIDTH / 2, currentTile.getY(true) + Globals.TILE_HEIGHT / 2);

        if (centerTile.x == centerNextTile.x) { //Verticaal -

            Vector2 centerMe = new Vector2(ingameX + (Globals.TILE_WIDTH / 2), ingameY + distance + (Globals.TILE_HEIGHT / 2));
            if (centerTile.y > centerNextTile.y) //Naar beneden
            {
                distance = 0 - distance;
                direction = Direction.Down;

            }
            if (centerTile.y < centerNextTile.y) { //Naar boven
                direction = Direction.Up;
                if (centerNextTile.x == centerMe.x && centerMe.y >= centerNextTile.y) {
                    usedTiles.add(currentTile);
                    currentTile = tempTile;
                }
            }

            if (direction == Direction.Down) {
                if (centerNextTile.x == centerMe.x && centerMe.y <= centerNextTile.y) {
                    usedTiles.add(currentTile);
                    currentTile = tempTile;
                }

            }
            ingameX = currentTile.getX() * Globals.TILE_WIDTH;
            ingameY += distance;

        } else { //Horizontaal |
            Vector2 centerMe = new Vector2(ingameX + (Globals.TILE_WIDTH / 2), ingameY + (Globals.TILE_HEIGHT / 2));
            if (centerTile.x < centerNextTile.x) { //Naar rechts
                direction = Direction.Right;
            }
            if (centerTile.x > centerNextTile.x) { //Naar links
                distance = 0 - distance;
                direction = Direction.Left;
            }

            if (direction == Direction.Right) {
                if (centerMe.x >= centerNextTile.x && centerNextTile.y == centerMe.y) {
                    usedTiles.add(currentTile);
                    currentTile = tempTile;
                }
            }
            if (direction == Direction.Left) {
                if (centerMe.x <= centerNextTile.x && centerNextTile.y == centerMe.y) {
                    usedTiles.add(currentTile);
                    currentTile = tempTile;
                }

            }
            ingameY = currentTile.getY() * Globals.TILE_HEIGHT;
            ingameX += distance;

        }
        return "Updated";
    }

    public void draw(SpriteBatch batch) {
        if (!isSpawned()) {
            return;
        }
        Texture t;
        //TODO make this dynamic
        if (isDead()) {
            t = TextureFactory.getTexture("blood");
        } else {
            t = TextureFactory.getTexture(super.getSprite() + "_hold_" + direction.toString().toLowerCase());

        }

        batch.draw(t, ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
        drawHealthBar(batch);

        if (Globals.DEBUG) {
            ShapeRenderer shapeRenderer = TextureGlobals.SHAPE_RENDERER;
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(1, 1, 1, 1);
            shapeRenderer.line(ingameX, ingameY, ingameX + Globals.TILE_WIDTH, ingameY + Globals.TILE_HEIGHT);
            shapeRenderer.line(ingameX, ingameY + Globals.TILE_HEIGHT, ingameX + Globals.TILE_WIDTH, ingameY);

            Tile tempTile = getNextPosition();

            if (tempTile != null) {
                Vector2 centerMe = new Vector2(ingameX + (Globals.TILE_WIDTH / 2), ingameY + (Globals.TILE_HEIGHT / 2));
                Vector2 centerTile = new Vector2(tempTile.getX(true) + Globals.TILE_WIDTH / 2, tempTile.getY(true) + Globals.TILE_HEIGHT / 2);
                shapeRenderer.setColor(1, 0, 0, 1);
                shapeRenderer.line(centerMe, centerTile);
            }
            shapeRenderer.end();
        }
    }

    public void drawHealthBar(SpriteBatch batch) {

        if (isDead()) {
            return;
        }
        double totalHitPoints = this.getTotalHitpoints();

        double widthPerHitPoints = Globals.TILE_WIDTH / totalHitPoints;
        double greenPart = this.getHitpoints() * widthPerHitPoints;
        double redPart = (totalHitPoints - this.getHitpoints()) * widthPerHitPoints;

        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
        p.setColor(Color.GREEN);
        p.fillRectangle(0, 0, 10, 1);
        Texture healthBarGreen = new Texture(p);

        Pixmap p1 = new Pixmap(1, 1, Pixmap.Format.RGB888);
        p1.setColor(Color.RED);
        p1.fillRectangle(0, 0, 10, 1);
        Texture healthBarRed = new Texture(p1);

        batch.draw(healthBarGreen, ingameX, ingameY + Globals.TILE_HEIGHT, (int) greenPart, Globals.TILE_HEIGHT / 5);
        batch.draw(healthBarRed, ingameX + (int) greenPart, ingameY + Globals.TILE_HEIGHT, (int) redPart, Globals.TILE_HEIGHT / 5);
    }

    public boolean isSpawned() {
        return currentTile != null;
    }

    /**
     * Spawn the unit on the offensive spawn position
     */
    public void spawn() {
        this.currentTile = owner.getOffensiveSpawnPosition();
        ingameX = currentTile.getX() * Globals.TILE_WIDTH;
        ingameY = currentTile.getY() * Globals.TILE_HEIGHT;
    }

    public List<int[]> getCorners() {
        List<int[]> retval;
        retval = new ArrayList();
        retval.add(new int[]{ingameX, ingameY});
        retval.add(new int[]{ingameX, ingameY + (Globals.TILE_HEIGHT)});
        retval.add(new int[]{ingameX + (Globals.TILE_WIDTH), ingameY});
        retval.add(new int[]{ingameX + (Globals.TILE_WIDTH), ingameY + (Globals.TILE_HEIGHT)});
        return retval;
    }

    public void die() {
        if (!Settings.isMuted()) {
            deathSound.play(1f);
        }
    }

    public float getX() {
        return ingameX;
    }

    public float getY() {
        return ingameY;
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
