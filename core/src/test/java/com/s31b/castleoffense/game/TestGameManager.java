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
public class TestGameManager {
    GameManager gm;
    
    @Before
    public void setUp() {
        gm = new GameManager();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestStartingGame() {
        //CoGame cg = gm.createGame();
        //assertEquals(cg, gm.getAllGames().get(0));
    }
}
