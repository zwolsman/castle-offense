/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.game.entity.Entity;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.player.Player;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Dennis
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEnitity {
    
    static Offensive o1;
    static Defensive d1;
    static Player p1;
    static Player p2;
    static int EntityStartID;
    
    @BeforeClass
    public static void SetupOffensive() {
        CoGame g = new CoGame(1);
        p1 = new Player(1, "Gebruiker1", g);
        p2 = new Player(2, "Gebruiker2", g);
        
        o1 = new Offensive(EntityType.Military, "Militair", "Advanced lvl 3 monster", null, p1, 400, 100, 10, 100);
        EntityStartID = Entity.getNextId();
        
        o1 = new Offensive(EntityType.Military, "Militair", "Advanced lvl 3 monster", null, p1, 400, 100, 10, 100);
        d1 = new Defensive(EntityType.Tower_Green, "Green Tower", "Advanced lvl 3 tower", null, p1, 600, 10, 100);
    }
    
    @Test
    public void TestAGetId(){
        assertEquals(EntityStartID + 1, o1.getId());
        assertEquals(EntityStartID + 2, d1.getId());
    }
    
    @Test
    public void TestBGetNextId(){
        assertEquals(EntityStartID + 2, Entity.getNextId());
        new Defensive(EntityType.Tower_Green, "Green Tower", "Advanced lvl 3 tower", null, p1, 600, 10, 100);
        assertEquals(EntityStartID + 3, Entity.getNextId());
        new Defensive(EntityType.Tower_Green, "Green Tower", "Advanced lvl 3 tower", null, p1, 600, 10, 100);
        assertEquals(EntityStartID + 4, Entity.getNextId());
    }
    
    @Test
    public void TestGetName(){
        assertEquals("Militair", o1.getName());
        assertEquals("Green Tower", d1.getName());
    }
    @Test
    public void TestGetDescription(){
        assertEquals("Advanced lvl 3 monster", o1.getDescription());
        assertEquals("Advanced lvl 3 tower", d1.getDescription());
    }
    @Test
    public void TestGetOwner(){
        assertEquals(p1, o1.getOwner());
        assertEquals(p1, d1.getOwner());
    }
    
    @Test
    public void TestGetPrice(){
        assertEquals(400, o1.getPrice(), 0);
        assertEquals(600, d1.getPrice(), 0);
    }
}
