/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.player;

import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.GameManager;
import com.s31b.castleoffense.player.*;
import java.rmi.RemoteException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Dennis
 */
public class TestCastle {
    Player p1;
    
    @Before
    public void createPlayer() throws RemoteException{
        GameManager gm = GameManager.getInstance();
        CoGame g = new CoGame(1);
        p1 = new Player(1, "Test gebruiker", g);
    }
    
     @Test
    public void TestCreateCastle(){
        Castle c = new Castle(p1);
        
        assertEquals("Castle does not have 10 hitpoints", 10, c.getHitpoints());
        assertEquals("Castle owner is not same owner as thrown in.", p1, c.getOwner());
    }
    
    @Test
    public void TestLoseHitpoints(){
        Castle c1 = new Castle(p1);
        Castle c2 = new Castle(p1);
        Castle c3 = new Castle(p1);
        Castle c4 = new Castle(p1);
        Castle c5 = new Castle(p1);
        
        
        c1.loseHitpoints(1);
        assertEquals(9, c1.getHitpoints());
        
        c2.loseHitpoints(-1);
        assertEquals(11, c2.getHitpoints());
        
        c3.loseHitpoints(0);
        assertEquals(10, c3.getHitpoints());
        
        c4.loseHitpoints(11);
        assertEquals(-1, c4.getHitpoints());
        
        c5.loseHitpoints(9);
        assertEquals(1, c5.getHitpoints());
    }
}
