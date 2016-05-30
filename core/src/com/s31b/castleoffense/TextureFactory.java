package com.s31b.castleoffense;

import com.badlogic.gdx.graphics.Texture;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.map.TileType;
import java.util.HashMap;
import java.util.Random;

/**
 * Creates and caches the texture's dynamicly
 *
 * @author Marvin Zwolsman
 */
public class TextureFactory {

    private static final HashMap cache = new HashMap();
    private static final Random rng = new Random();

    /**
     * Get a texture by name. It will apend .png if needed.
     *
     * @param name the name of the texture (with or without .png)
     * @return The texture if found otherwise there will be an exception thrown
     */
    public static Texture getTexture(String name) {

        if ("".equals(name)) {
            return null;
        }
        if (!name.endsWith(".png")) {
            name = name + ".png";
        }

        if (!cache.containsKey(name)) {
            cache.put(name, new Texture(name));
        }
        return (Texture) cache.get(name);
    }

    /**
     * Get a texture based on a tile. You can only use TileType.Grass and
     * TileType.Path
     *
     * @param tile The tile that will need to load
     * @return The texture for the tile
     */
    public static Texture getTexture(Tile tile) {

        if (tile.getTexture() == null) {
            String textureName = "";
            if (tile.getType() == TileType.Grass) {
                //Tile 01 t/m 04 is grass
                textureName = "tile_0" + (rng.nextInt(4) + 1);
            }

            //138 verticaal
            //111 horizontaal
            //Up right = 136, Up left = 137
            //Down right = 109, Down left = 110
            if (tile.getType() == TileType.Path) {

                Tile[][] walkables = tile.getMap().getAllWalkableTiles2D();

                int xLeft = tile.getX() - 1;
                int xRight = tile.getX() + 1;
                int yDown = tile.getY() - 1;
                int yUp = tile.getY() + 1;

                if (xLeft < Globals.TILES_X && xLeft > 0 && walkables[xLeft][tile.getY()] != null) { //Left is a pad?
                    if (yDown >= 0 && walkables[tile.getX()][yDown] != null) //Down is path?
                    {
                        textureName = "tile_110";
                    } else if (yUp < Globals.TILES_Y && walkables[tile.getX()][yUp] != null) //Up is path
                    {
                        textureName = "tile_137";
                    } else { //I'm just horizontal
                        textureName = "tile_111";
                    }
                } else if (xRight > -1 && xRight < Globals.TILES_X && walkables[xRight][tile.getY()] != null) {
                    if (yDown >= 0 && walkables[tile.getX()][yDown] != null) //Down is path?
                    {
                        textureName = "tile_109";
                    } else if (yUp < Globals.TILES_Y && walkables[tile.getX()][yUp] != null) //Up is path
                    {
                        textureName = "tile_136";
                    } else { //I'm just horizontal
                        textureName = "tile_111";
                    }
                } else {
                    textureName = "tile_138";
                }
            }
            System.out.println(textureName);
            if ("".equals(textureName)) {
                System.out.println("No texture found for" + tile.toString());
                return null;
            }
            tile.setTexture(getTexture("Tiles/" + textureName));
        }

        return tile.getTexture();

    }
}
