package com.s31b.castleoffense.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.TextureGlobals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The map for a game.
 *
 * @author Goos
 */
public class Map {

    private Tile selectedTile;
    private Tile[][] tiles;
    //A temporary map, will be dynamic later
    private static final String tempMask[] = {
        "0000000111111111000000000000000000",
        "0000000100000001000000000000000000",
        "0000000111100001000000011111100000",
        "0000000000100001000000010000100000",
        "0000001111100001000000010000100000",
        "0000001000000001000111110000100000",
        "0000001000000001000100000000100000",
        "0000001000000001000100000000100000",
        "0000001000000001000100000000100000",
        "0000001000000001000100000000100000",
        "2111111000000001111100000000100000",
        "0000000000000000000000000000200000"};

    public Map() {
        tiles = new Tile[Globals.TILES_X][Globals.TILES_Y];
        initMap();
    }

    /**
     * Initializes a map from a string.
     */
    private void initMap() {
        for (int x = 0; x < Globals.TILES_X; x++) {
            for (int y = 0; y < Globals.TILES_Y; y++) {
                TileType type = TileType.values()[tempMask[y].charAt(x) - (int) '0'];
                if (type == TileType.Unknown) {
                    continue;
                }
                tiles[x][y] = new Tile(x, y, type, this);
                //System.out.println(tiles[x][y]);
            }
        }
    }

    //Not implement yet
    public void createPlayfield() {
        // instantiate the playfield
        throw new UnsupportedOperationException();
    }

    /**
     * Get all the tiles of the map
     *
     * @return A List of tiles
     */
    public List<Tile> getAllTiles() {

        List<Tile> tempTiles = new ArrayList<Tile>();
        for (int x = 0; x < Globals.TILES_X; x++) {
            for (int y = 0; y < Globals.TILES_Y; y++) {
                tempTiles.add(tiles[x][y]);
            }
        }
        return Collections.unmodifiableList(tempTiles);
    }

    /**
     * Get a list of tiles that a unit can possibly walk on
     *
     * @return A list of walkable tiles
     */
    public List<Tile> getAllWalkableTiles() {

        List<Tile> tempTiles = new ArrayList<Tile>();
        for (int x = 0; x < Globals.TILES_X; x++) {
            for (int y = 0; y < Globals.TILES_Y; y++) {
                System.out.println(x + ", " + y);
                if (tiles[x][y].isWalkable()) {
                    tempTiles.add(tiles[x][y]);
                }
            }
        }
        return tempTiles;
    }

    /**
     * Get a 2D array of tiles that a unit can possibly walk on. If a unit can
     * not move on it it will be a null value in the 2D array
     *
     * @return A 2D array of walkable tiles
     */
    public Tile[][] getAllWalkableTiles2D() {
        Tile[][] temp = new Tile[Globals.TILES_X][Globals.TILES_Y];
        for (int x = 0; x < Globals.TILES_X; x++) {
            for (int y = 0; y < Globals.TILES_Y; y++) {
                if (tiles[x][y].isWalkable()) {
                    temp[x][y] = tiles[x][y];
                }
            }
        }
        return temp;
    }

    public Tile getSelectedTile() {

        int xMouse = Gdx.input.getX();
        int yMouse = Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight());

        int x = (int) Math.ceil(xMouse / Globals.TILE_WIDTH);
        int y = (int) Math.ceil(yMouse / Globals.TILE_HEIGHT);
        if (Globals.DEBUG) {
            System.out.println("X raw: " + xMouse);
            System.out.println("Y raw: " + yMouse);
            System.out.println("============");
            System.out.println("math ceil x: " + Math.ceil(xMouse / Globals.TILE_WIDTH));
            System.out.println("math ceil y: " + Math.ceil(yMouse / Globals.TILE_HEIGHT));
        }
        if (x >= Globals.TILES_X - 1 || x < 0 || y >= Globals.TILES_Y - 1 || y < 0) {
            return null;
        }
        return tiles[x][y];
    }

    public void draw() {
        //TextureGlobals.SPRITE_BATCH = new SpriteBatch();
        BitmapFont font = new BitmapFont();
        if (Globals.DEBUG) {
            TextureGlobals.SHAPE_RENDERER.begin(ShapeRenderer.ShapeType.Line);
        }
        for (int x = 0; x < Globals.TILES_X; x++) {
            for (int y = 0; y < Globals.TILES_Y; y++) {
                int ingameX = x * Globals.TILE_WIDTH, ingameY = y * Globals.TILE_HEIGHT;
                Tile tile = tiles[x][y];
                if (tile == null) {
                    continue;
                }

                Texture t = null;
                if (tile.getType() == TileType.Grass || tile.getType() == TileType.Path) {
                    t = TextureFactory.getTexture(tile);
                } else {
                    t = TextureFactory.getTexture(tile.getType().name().toLowerCase());
                }

                if (t == null) {
                    continue;
                }
                TextureGlobals.SPRITE_BATCH.draw(t, ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);

                if (Globals.DEBUG) {
                    font.draw(TextureGlobals.SPRITE_BATCH, String.format("X: %s,\r\nY: %s", x, y), ingameX, ingameY + 40, 40, 40, false);
                    TextureGlobals.SHAPE_RENDERER.rect(ingameX, ingameY, Globals.TILE_WIDTH, Globals.TILE_HEIGHT);
                }

            }
        }
        if (Globals.DEBUG) {
            TextureGlobals.SHAPE_RENDERER.end();
        }
        //TextureGlobals.SPRITE_BATCH.begin();
    }

    public void mouseClicked(int x, int y) {
        for (Tile item : getAllTiles()) {
            if (item.contains(x, y)) {
                continue;
            }
            selectedTile = item;
            System.out.println(item.toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Map)) {
            return false;
        }
        Map m = (Map) other;
        return this.tiles == m.tiles;
    }
}
