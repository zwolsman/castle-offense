package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.s31b.castleoffense.AudioFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.Settings;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;

/**
 * The defensive class i used for objects that defends a player. Eg a tower
 *
 * @author Goos
 */
public class Defensive extends Entity {

    private int damagePerSecond;
    private int range;
    private Offensive target;
    private Sound shootSound = null;
    private float deltaCounter;
    private boolean shooting;
    private float attackSpeed;

    private Tile position;

    public Tile getPosition() {
        return position;
    }

    /**
     * Constructor used for Unit Tests
     *
     * @param type
     * @param name
     * @param descr
     * @param sprite
     * @param owner
     * @param price
     * @param dps
     * @param range
     */
    public Defensive(EntityType type, String name, String descr, String sprite, Player owner, int price, int dps, int range) {
        super(type, name, descr, sprite, price, owner);
        damagePerSecond = dps;
        this.range = range;
        position = new Tile(0, 0);
        target = null;
        deltaCounter = 0f;
        shooting = true;
        shootSound = null;
        attackSpeed = 1f;
    }

    /**
     * Constructor for general use (From database)
     *
     * @param data
     * @param owner
     */
    public Defensive(DefensiveDAO data, Player owner) {
        super(EntityType.getTypeFromString(data.getType()), data.getName(), data.getDescr(), data.getSprite(), data.getPrice(), owner);
        damagePerSecond = data.getDamage();
        this.range = data.getRange();
        position = new Tile(0, 0);
        target = null;
        deltaCounter = 0f;
        shooting = true;
        attackSpeed = 1f;
    }

    /**
     * Update the position of the defensive object if needed.
     *
     * @param position
     */
    public void setPosition(Tile position) {
        this.position = position;
    }

    /**
     * Damage per second (DPS) of the object
     *
     * @return
     */
    public double getDamagePerSecond() {
        return damagePerSecond;
    }

    /**
     * @param damagePerSecond the damagePerSecond to set
     */
    public void setDamagePerSecond(int damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    public void update() {

    }

    public void draw() {

        if (shootSound == null) {
            shootSound = AudioFactory.getSound("laser.wav");
        }
        TextureGlobals.SPRITE_BATCH.end();
        ShapeRenderer shapeRenderer = TextureGlobals.SHAPE_RENDERER;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (Globals.DEBUG) {
            shapeRenderer.circle(position.getX(true) + Globals.TILE_WIDTH / 2, position.getY(true) + Globals.TILE_HEIGHT / 2, range * Globals.TILE_WIDTH);
        }

        if (targetAquired() && inRange(target) && (Globals.DEBUG || shooting)) {
            if (Globals.DEBUG) {
                shapeRenderer.setColor(Color.WHITE);
            } else if (shooting) {
                shapeRenderer.setColor(Color.CYAN);
                shooting = !shooting;
            }
            shapeRenderer.rectLine(position.getX(true) + Globals.TILE_WIDTH / 2, position.getY(true) + Globals.TILE_HEIGHT / 2,
                    target.getX() + Globals.TILE_WIDTH / 2, target.getY() + Globals.TILE_HEIGHT / 2, 2f);
        }
        shapeRenderer.end();

        TextureGlobals.SPRITE_BATCH.begin();
        TextureGlobals.SPRITE_BATCH.draw(
                TextureFactory.getTexture(super.getSprite()),
                position.getX() * Globals.TILE_WIDTH,
                position.getY() * Globals.TILE_HEIGHT,
                Globals.TILE_WIDTH, Globals.TILE_HEIGHT);

    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }

    public void setTarget(Offensive o) {
        if (o.getOwner() != getOwner()) {
            target = o;
        }
    }

    public boolean targetAquired() {
        return target != null && !target.isDead() && !target.isAtEnemyCastle();
    }

    public boolean inRange(Offensive o) {
        for (int[] i : o.getCorners()) {
            int ox = i[0];
            int oy = i[1];

            int dx = position.getX(true) + Globals.TILE_WIDTH / 2;
            int dy = position.getY(true) + Globals.TILE_HEIGHT / 2;

            int xDif = Math.abs(ox - dx);
            int yDif = Math.abs(oy - dy);

            if (Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2)) < (range * Globals.TILE_WIDTH)) {
                return true;
            }
        }
        return false;
    }

    public void deleteTarget() {
        target = null;
    }

    public Offensive getTarget() {
        return target;
    }

    public void dealDamage() {
        deltaCounter -= Gdx.graphics.getDeltaTime();
        if (deltaCounter < 0.1f || deltaCounter > (attackSpeed - 0.1f)) {
            shooting = true;
        }
        if (deltaCounter <= 0f) {
            deltaCounter += attackSpeed;
            if (shootSound != null && !Settings.getInstance().isMuted()) {
                shootSound.play(0.7f);
            }
            target.dealDamage(damagePerSecond * attackSpeed, this);
        }
    }
}
