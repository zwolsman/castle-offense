/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game.map;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.map.Map;
import com.s31b.castleoffense.map.Tile;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import test.java.com.s31b.castleoffense.TestListener;

/**
 *
 * @author Dennis
 */
public class TestMap {
    Map map;
    
    @BeforeClass
    public static void SetUpHeadless() throws InterruptedException{
        HeadlessApplication ha = new HeadlessApplication(new TestListener());
        Thread.sleep(50);
    }
    
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
        int expected = 72;
        assertEquals("Count of tiles. ", expected, map.getAllWalkableTiles().size());
    }
    
    @Test
    public void TestGetAllWalkableTiles2D(){
        int expected = 34;
        assertEquals("Count of tiles. ", expected, map.getAllWalkableTiles2D().length);
    }
    
    @Test
    public void TestGetSelectedTile(){
        assertEquals(new Tile(0, 0), map.getSelectedTile());
    }
    
    @Test
    public void TestMouseClicked(){
        map.mouseClicked(0, 0);
        map.mouseClicked(233, 0);
        map.mouseClicked(0, 35362);
    }
    
    @Test
    public void TestEquals(){
        Tile t1 = new Tile(0, 1);
        Map m1 = new Map();
        Map m2 = new Map();
        
        
        assertTrue("m1 is not equals with m2", m1.equals(m2));
        
        assertTrue("m1 is not equals with g1",m1.equals(m1));
        assertFalse("m1 is equals with null",m1.equals(null));
        assertFalse("m1 is not equals with tile",m1.equals(51));
    }
    
    @Test
    public void TestGetSpawnPoints(){
        assertEquals(2, map.getSpawnPoints().size());
    }
}