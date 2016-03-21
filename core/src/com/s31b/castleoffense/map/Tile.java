package com.s31b.castleoffense.map;

import java.awt.image.BufferedImage;

/**
 *
 * @author GoosLaptop
 */
public class Tile {
    private int ownerId;
    private boolean walkable;
    private boolean buildable;
    
    private BufferedImage sprite;

    public Tile(int ownerId, boolean walkable, boolean buildable, BufferedImage sprite) {
        this.ownerId = ownerId;
        this.walkable = walkable;
        this.buildable = buildable;
        this.sprite = sprite;
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
    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.type = TileType.Dirt;
    }
    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
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
