/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.s31b.castleoffense.game.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dennis
 */
public class TestWave {
    CoGame game;

    @Before
    public void CreateGame(){
        
    }
    
    @Test
    public void TestCreateWave() {
        game = GameManager.getInstance().createGame();
        /*
        Wave w1 = new Wave(1, game);
        assertEquals(1, w1.getNumber());
        
        Wave w2 = new Wave(-1, game);
        assertEquals(-1, w2.getNumber());
        
        Wave w3 = new Wave(999999999, game);
        assertEquals(999999999, w3.getNumber());*/
    }
    
    @Test
    public void TestEndWave(){
        //game = GameManager.getInstance().createGame();
        /*Wave w1 = new Wave(1, game);
        w1.endWave(1);
        w1.endWave(2);
        assertEquals(new Wave(2, game), game.getCurrentWave());
        */
        /*w1.endWave(2);
        w1.endWave(1);
        assertEquals(new Wave(3, game), game.getCurrentWave());
        
        
        w1.endWave(2);
        w1.endWave(3);
        w1.endWave(2);
        w1.endWave(-1);
        w1.endWave(2399395);
        assertEquals(new Wave(3, game), game.getCurrentWave());*/
    }
    
    @Test
    public void TestAddEntity(){
        
    }
}
