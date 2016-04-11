package com.s31b.castleoffense.map;

import com.badlogic.gdx.graphics.Texture;
import com.s31b.castleoffense.Globals;

/**
 * A tile for the map
 *
 * @author GoosLaptop
 */
public class Tile {

    private boolean buildable;
    private Texture texture;
    private Map map;

    //private BufferedImage sprite;
//    public Tile(int ownerId, boolean buildable, BufferedImage sprite) {
//        this.ownerId = ownerId;
//        this.sprite = sprite;
//    }
    /**
     * Create a grass tile at a specific position
     *
     * @param x The X of the tile (0 - Globals.TILES_X)
     * @param y The Y of the tile (0 - Globals.TILES_Y)
     *
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = TileType.Grass;
    }

    /**
     * Create a tile at a specific position
     *
     * @param x The X of the tile (0 - Globals.TILES_X)
     * @param y The Y of the tile (0 - Globals.TILES_Y)
     * @param type The type of the tile
     * @param m The map the tile will be used on
     */
    public Tile(int x, int y, TileType type, Map m) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.map = m;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture t) {
        texture = t;
    }

    public Map getMap() {
        return map;
    }

    /**
     * Calculates if a given point (x,y) is in the tile. The x and y need to be
     * relative to the screen, not to the grid
     *
     * @param x The X position on the screen
     * @param y The Y position on the scren
     * @return TRUE if it is in the bounds otherwise false
     */
    public boolean contains(int x, int y) {

        int ingameX = getX() * Globals.TILE_WIDTH;
        int ingameY = getY() * Globals.TILE_HEIGHT;
        return x >= ingameX && x <= ingameX + Globals.TILE_WIDTH && y >= ingameY && y <= ingameY + Globals.TILE_HEIGHT;
    }

    /**
     * Calculates of a given point is at the origin of the tile (Left bottom)
     *
     * @param x The X position on the screen
     * @param y The Y position on the sceen
     * @return
     */
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
