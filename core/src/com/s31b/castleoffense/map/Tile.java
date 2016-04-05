package com.s31b.castleoffense.map;

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

}
