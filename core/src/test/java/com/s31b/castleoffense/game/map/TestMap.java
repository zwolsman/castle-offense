/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game.map;

import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.map.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Dennis
 */
public class TestMap {
    Map map;
    
    @Before
    public void setUpMap(){
        map = new Map();
    }
    
    @Test
    public void TestMapInitialize(){
        int expected = Globals.TILES_X * Globals.TILES_Y;
        map.getAllTiles();
        
        
        assertEquals("Count of tiles. ", expected, map.getAllTiles().size());
    }
    
    @Test
    public void TestGetAllWalkableTiles(){
        int expected = 46;
        assertEquals("Count of tiles. ", expected, map.getAllWalkableTiles().size());
    }
    
    @Test
    public void TestGetAllWalkableTiles2D(){
        int expected = 17;
        assertEquals("Count of tiles. ", expected, map.getAllWalkableTiles2D().length);
    }
}
