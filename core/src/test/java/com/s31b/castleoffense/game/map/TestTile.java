/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game.map;

import com.s31b.castleoffense.map.Map;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.map.TileType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dennis
 */
public class TestTile {
    Tile t1;
    Tile t2;
    Map map;
    
    @Before
    public void SetupTiles(){
        t1 = new Tile(3, 4);
        
        map = new Map();
        t2 = new Tile(4, 3, TileType.Path, map);
    }
    
    @Test
    public void TestCreateTile(){
        new Tile(1, 1);
    }
    
    @Test
    public void TestGetX(){
        assertEquals(3, t1.getX());
        assertEquals(120, t1.getX(true));
    }
    
    @Test
    public void TestGetY(){
        assertEquals(4, t1.getY());
        assertEquals(160, t1.getY(true));
    }
    
    @Test
    public void TestIsBuildable(){
        assertFalse(t1.isBuildable());
    }
    
    @Test
    public void TestIsWalkable(){
        assertFalse(t1.isWalkable());
    }
    
    @Test
    public void TestGetType(){
        assertEquals(TileType.Grass, t1.getType());
    }
    
    @Test
    public void TestSetBuildable(){
        t1.setBuildable(true);
        assertTrue(t1.isBuildable());
        t1.setBuildable(true);
        assertTrue(t1.isBuildable());
        t1.setBuildable(false);
        assertFalse(t1.isBuildable());
        t1.setBuildable(false);
        assertFalse(t1.isBuildable());
    }
    
    @Test
    public void TestIsAtOrigin(){
        assertTrue(t1.isAtOrigin(120, 160));
        assertFalse(t1.isAtOrigin(121, 160));
        assertFalse(t1.isAtOrigin(120, 161));
        assertFalse(t1.isAtOrigin(119, 161));
    }
    
    @Test
    public void TestGetMap(){
        assertNull(t1.getMap());
        assertEquals(map, t2.getMap());
    }

}
