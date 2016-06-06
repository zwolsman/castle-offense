/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.GameManager;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.map.Map;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.map.TileType;
import com.s31b.castleoffense.player.Player;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dennis
 */
public class TestOffensive {
    Offensive o1;
    Offensive o2;
    Offensive o3;
    
    @Before
    public void SetupOffensive() throws RemoteException {
        CoGame g = GameManager.getInstance().createGame();
        Player p1 = new Player(0, "Gebruiker", g);
        Player p2 = new Player(1, "Testgebruiker", g);
        o1 = new Offensive(EntityType.Military, "Militair", "Advanced lvl 3 monster", null, p1, 400, 100, 10, 100);
        o2 = new Offensive(EntityType.Military, "Police", "Advanced lvl 3 monster", null, p1, 500, 110, 15, 110);
        o3 = new Offensive(EntityType.Military, "Militair", "Advanced lvl 3 monster", null, p2, 600, 120, 20, 120);
    }
    
    @Test
    public void TestGetHitpoints(){
        assertEquals(100, o1.getHitpoints(), 0);
        assertEquals(110, o2.getHitpoints(), 0);
        assertEquals(120, o3.getHitpoints(), 0);
    }
    
    @Test
    public void TestGetMovementSpeed(){
        assertEquals(10, o1.getMovementSpeed());
        assertEquals(15, o2.getMovementSpeed());
        assertEquals(20, o3.getMovementSpeed());
    }
    
    @Test
    public void TestGetKillReward(){
        assertEquals(100, o1.getKillReward());
        assertEquals(110, o2.getKillReward());
        assertEquals(120, o3.getKillReward());
    }
    
    @Test
    public void TestGetPosition(){
        assertNull(o1.getPosition());
        assertNull(o2.getPosition());
        assertNull(o3.getPosition());
    }
    @Test
    public void TestGetNextPosition(){
        o1.spawn();
        o2.spawn();
        o3.spawn();
        
        
        assertEquals(new Tile(1, 10, TileType.Path, new Map()), o1.getNextPosition());
        assertEquals(new Tile(1, 10, TileType.Path, new Map()), o2.getNextPosition());
        assertEquals(new Tile(28, 10, TileType.Path, new Map()), o3.getNextPosition());
    }
    @Test
    public void TestRemoveHealth(){
        o1.removeHealth(-5);
        assertEquals(105, o1.getHitpoints(), 0);
        
        o1.removeHealth(5);
        assertEquals(100, o1.getHitpoints(), 0);
        
        o1.removeHealth(0);
        assertEquals(100, o1.getHitpoints(), 0);
    }
    
    @Test
    public void TestSpawn(){
        assertFalse(o1.isSpawned());
        assertFalse(o2.isSpawned());
        assertFalse(o3.isSpawned());
        
        o1.spawn();
        o2.spawn();
        o3.spawn();
        
        assertTrue(o1.isSpawned());
        assertTrue(o2.isSpawned());
        assertTrue(o3.isSpawned());
    }
    
    
    @Test
    public void TestGetCorners(){
        o1.getCorners();
    }
    
    @Test
    public void TestGetX(){
        o1.spawn();
        o3.spawn();
        assertEquals(0, o1.getX(), 0);
        assertEquals(0, o2.getX(), 0);
        assertEquals(1120, o3.getX(), 0); ////Location 28 * tile width (40)
    }
    
    @Test
    public void TestGetY(){
        o1.spawn();
        assertEquals(400, o1.getY(), 0); ////Location 10 * tile height (40)
        assertEquals(0, o2.getY(), 0);
        assertEquals(0, o3.getY(), 0);
    }
}
