package com.s31b.castleoffense.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Goos
 */
public class Map {
    private Tile[][] tiles;
	private static final String tempMask[] = {
			  "00000000002222200",
			  "11100000002030200",
			  "00100000002212200",
			  "00100000000010000",
			  "00100111111110000",
			  "00100100000000000",
			  "00100100000000000",
			  "00100111111111000",
			  "00100000000001000",
			  "00100000000001000",
			  "00111111111111000",
			  "00000000000000000",};
    public Map() {
        tiles = new Tile[Globals.TILES_X][Globals.TILES_Y];
   
        initMap();
    }
    
    private void initMap() {
        for(int x = 0; x < Globals.TILES_X; x++) {
            for(int y = 0; y < Globals.TILES_Y; y++)
            {
                TileType type = TileType.values()[tempMask[y].charAt(x) - (int)'0'];
                if(type == TileType.Unknown)
                    continue;
                tiles[x][y] = new Tile(x,y, type);
            }
        }
    }
    
    public void createPlayfield() {
        // instantiate the playfield
        throw new UnsupportedOperationException();
    }
    
    public List<Tile> getAllTiles() {
        
        List<Tile> tempTiles = new ArrayList<Tile>();
        for(int x = 0; x < Globals.TILES_X; x++) {
            for(int y = 0; y < Globals.TILES_Y; y++)
            {
                tempTiles.add(tiles[x][y]);
            }
        }
        return Collections.unmodifiableList(tempTiles);
    }

    BitmapFont font = new BitmapFont(); 
    public void draw() {

        for(int x = 0; x < Globals.TILES_X; x++) {
            for(int y = 0; y < Globals.TILES_Y; y++)
            {
                int ingameX = x * Globals.TILE_WIDTH, ingameY = y * Globals.TILE_HEIGHT;
                Tile tile = tiles[x][y];
                if(tile == null)
                {
                    System.out.println("TILE IS NULL; x: " + x + " y:" + y);
                    continue;
                }
                Texture t = TextureFactory.getTexture(tile.getType().name().toLowerCase());
                Globals.SPRITE_BATCH.draw(t, ingameX, ingameY);
                
               //font.draw
                
                font.draw(Globals.SPRITE_BATCH, String.format("X: %s, Y: %s", x, y), ingameX, ingameY + 40, 40, 40, false);
            }
        }

    }
}
