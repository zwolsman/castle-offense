/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.GameManager;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dennis
 */
public class TestDefensive {
    
    Defensive def1;
    Defensive def2;
    Defensive def3;
    Player p1;
    Player p2;
    
    @Before
    public void SetupDefensive() throws RemoteException {
        CoGame g = GameManager.getInstance().createGame();
        p1 = new Player(1, "Gebruiker", g);
        p2 = new Player(0, "", g);
        def1 = new Defensive(EntityType.Blue, "", "", null, p1, 0, 40, 1);
        def2 = new Defensive(EntityType.Blue, "", "", null, p1, 0, 250, 20);
        def3 = new Defensive(EntityType.Blue, "", "", null, p1, 0, 500, 30);
    }

    @Test
    public void TestSetPosition(){
        def1.setPosition(new Tile(1,3));
        def2.setPosition(new Tile(1,3));
        def3.setPosition(new Tile(5,1));
    }
    
    @Test
    public void TestGetDamagePerSecond(){
        assertEquals(40, def1.getDamagePerSecond(), 0);
        assertEquals(250, def2.getDamagePerSecond(), 0);
        assertEquals(500, def3.getDamagePerSecond(), 0);
    }
    
    @Test
    public void TestGetRange(){
        assertEquals(1, def1.getRange(), 0);
        assertEquals(20, def2.getRange(), 0);
        assertEquals(30, def3.getRange(), 0);
    }
    
    @Test
    public void TestSetRange(){
        def1.setRange(-1);
        assertEquals(-1, def1.getRange(), 0);
        
        def1.setRange(0);
        assertEquals(0, def1.getRange(), 0);
        
        def1.setRange(1);
        assertEquals(1, def1.getRange(), 0);
        
        def1.setRange(10);
    }
    
    @Test
    public void TestInRange(){
        Offensive off1 = new Offensive(EntityType.Blue, "", "", null, p1, 0, 0, 0, 0);
        off1.spawn();
        
        def1.setPosition(new Tile(2, 10));
        def2.setPosition(new Tile(50, 50));
        def3.setPosition(new Tile(5, 5));
        
        assertTrue("off1 is in range of o1", def1.inRange(off1));
        assertFalse("off1 is in range of o2",def2.inRange(off1));
        assertTrue("off1 is in range of o3",def3.inRange(off1));
    }
    
    @Test
    public void TestDealDamage(){
        ///Deal Damage throw NullPointer because using Gdx.graphics.getDeltaTime()
        /*Offensive off1 = new Offensive(EntityType.Blue, "", "", null, p2, 0, 10, 10, 10);
        def1.setTarget(off1);
        assertNull(def1.getTarget());
        for(int i=0; i < 1000; i++){
            def1.dealDamage();
        }
        
       assertEquals(0, off1.getHitpoints(), 0);*/
    }
    
    @Test
    public void TestSetTarget(){
        Offensive off1 = new Offensive(EntityType.Blue, "", "", null, p2, 0, 10, 10, 10);
        Offensive off2 = new Offensive(EntityType.Blue, "", "", null, p1, 0, 10, 10, 10);
        Offensive off3 = new Offensive(EntityType.Blue, "", "", null, p2, 0, 10, 10, 10);
        
        def1.setTarget(off1);
        ////def 2 is same Owner as off2
        def2.setTarget(off2);
        def3.setTarget(off3);
        
        assertEquals(off1, def1.getTarget());
        assertNull(def2.getTarget());
        assertEquals(off3, def3.getTarget());
    }
    
    
    @Test
    public void TestDeleteTarget(){
        Offensive off1 = new Offensive(EntityType.Blue, "", "", null, p2, 0, 10, 10, 10);
        Offensive off2 = new Offensive(EntityType.Blue, "", "", null, p1, 0, 10, 10, 10);
        Offensive off3 = new Offensive(EntityType.Blue, "", "", null, p2, 0, 10, 10, 10);
        
        def1.setTarget(off1);
        def2.setTarget(off2);
        def3.setTarget(off3);
        
        def1.deleteTarget();
        def2.deleteTarget();
        def3.deleteTarget();
        
        assertNull(def1.getTarget());
        assertNull(def2.getTarget());
        assertNull(def3.getTarget());
    }
    
    @Test
    public void TestTargetAquired(){
         Offensive off1 = new Offensive(EntityType.Blue, "", "", null, p2, 0, 10, 10, 10);
         
         def1.deleteTarget();
         assertFalse(def1.targetAquired());
         def1.setTarget(off1);
         assertTrue(def1.targetAquired());
    }
}
