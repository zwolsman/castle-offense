package com.s31b.castleoffense.map;

import com.s31b.castleoffense.Globals;
import java.awt.image.BufferedImage;

/**
 *
 * @author GoosLaptop
 */
public class Tile {

    private int ownerId;
    private boolean buildable;

    private BufferedImage sprite;

    public Tile(int ownerId, boolean buildable, BufferedImage sprite) {
        this.ownerId = ownerId;
        this.sprite = sprite;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = TileType.Dirt;
    }

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    private int x, y;
    private TileType type;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileType getType() {
        return type;
    }

    public boolean isWalkable() {
        return type == TileType.Path;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public boolean contains(int x, int y) {

        int ingameX = getX() * Globals.TILE_WIDTH;
        int ingameY = getY() * Globals.TILE_HEIGHT;
        return x >= ingameX && x <= ingameX + Globals.TILE_WIDTH && y >= ingameY && y <= ingameY + Globals.TILE_HEIGHT;
    }

    public boolean isAtOrigin(int x, int y) {
        int ingameX = getX() * Globals.TILE_WIDTH;
        int ingameY = getY() * Globals.TILE_HEIGHT;
        return ingameX == x && ingameY == y;
    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, type: %s", getX(), getY(), getType());
    }
}
