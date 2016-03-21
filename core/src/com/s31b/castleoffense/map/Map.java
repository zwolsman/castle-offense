package com.s31b.castleoffense.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Goos
 */
public class Map {
    private List<Tile> tiles;

    public Map() {
        tiles = new ArrayList<Tile>();
    }
    
    public void createPlayfield() {
        // instantiate the playfield
        throw new UnsupportedOperationException();
    }
    
    public List<Tile> getAllTiles() {
        return Collections.unmodifiableList(tiles);
    }
}
