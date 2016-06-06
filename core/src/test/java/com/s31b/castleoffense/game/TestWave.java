/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.*;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.player.Player;
import test.java.com.s31b.castleoffense.TestListener;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dennis
 */
public class TestWave {
    CoGame game;
    Player p1;
    Player p2;
    Wave w1;

    @Before
    public void CreateGame() throws InterruptedException{
        HeadlessApplication ha = new HeadlessApplication(new TestListener());
        Thread.sleep(500);
        
        game = new CoGame(1);
        p1 = new Player(0, "Testgebruiker", game);
        p2 = new Player(1, "Naam", game);
        w1 = new Wave(1, game);
    }
    
    @Test
    public void TestSpawnWave() throws InterruptedException{
        
        ////This unit will run
        Offensive off1 = new Offensive(EntityType.Blue, "", "", "", p1, 0, 100, 1, 20);
        ///This unit will die
        Offensive off2 = new Offensive(EntityType.Blue, "", "", "", p1, 0, 0, 0, 40);
        
        int expected_gold_p1 = p1.getGold();
        int expected_gold_p2 = p2.getGold() + off2.getKillReward();
        
        w1.addOffensive(off1);
        w1.addOffensive(off2);
        w1.endWave();
        w1.endWave();
                
        for(int i = 0; i < 2500; i++){
            w1.update();
            
        }
        w1.endWave();
        assertTrue("offensive is not spawned", off1.isSpawned());
        assertTrue("offensive is not dead", off2.isDead());
        assertEquals("Player 1 gold", expected_gold_p1, p1.getGold());
        //assertEquals("Player 2 gold", expected_gold_p2, p2.getGold());
    }
    
    @Test
    public void TestCreateWave() {
        game = new CoGame(1);
        
        Wave w1 = new Wave(1, game);
        assertEquals(1, w1.getNumber());
        
        Wave w2 = new Wave(-1, game);
        assertEquals(-1, w2.getNumber());
        
        Wave w3 = new Wave(999999999, game);
        assertEquals(999999999, w3.getNumber());
    }
    
    @Test
    public void TestGetWaveNumber(){
        Wave w1 = new Wave(1, game);
        assertEquals(1, w1.getNumber());
        
        Wave w2 = new Wave(-1, game);
        assertEquals(-1, w2.getNumber());
        
        Wave w3 = new Wave(0, game);
        assertEquals(0, w3.getNumber());
    }
    
    @Test
    public void TestEndWave(){
        Wave w1 = new Wave(1, game);
        
        ///Test wave with not all players ended the wave
        w1.endWave();
        w1.endWave();
        w1.endWave();
        w1.endWave();
        w1.endWave();
        game.update();
        assertEquals(1, game.getCurrentWave().getNumber());
        
        w1.endWave();
        w1.endWave();
        
        w1.endWave();
        w1.endWave();
    }
    
    @Test
    public void TestAddOffensive(){///Spawn Wave uses GDX this will throw NullPointer
        
        Offensive o1 = new Offensive(EntityType.Police, "test", "test", "", p1, 100, 100, 10, 15);
        Offensive o2 = new Offensive(EntityType.Police, "test", "test", "", p1, 200, 120, 10, 15);
        Offensive o3 = new Offensive(EntityType.Police, "test", "test", "", p1, 300, 130, 10, 15);
        
        w1.addOffensive(o1);
        w1.addOffensive(o2);
        w1.addOffensive(o3);
        
        w1.endWave();
        w1.endWave();
    }
}
