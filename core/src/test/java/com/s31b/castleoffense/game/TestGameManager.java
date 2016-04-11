/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.s31b.castleoffense.game.*;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dennis
 */
public class TestGameManager {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestStartingGame() {
        /*int expected_games = GameManager.getInstance().getAllGames().size() + 1;
        CoGame cg = GameManager.getInstance().createGame();
        assertEquals(expected_games, GameManager.getInstance().getAllGames().size());
        assertEquals(cg, GameManager.getInstance().getAllGames().get(1));*/
    }
    
    @Test
    public void TestGetters(){
        assertEquals(2, GameManager.getInstance().getPlayerRowAmount());
        assertEquals(1000, GameManager.getInstance().getGoldPerWave());
        assertEquals(60000, GameManager.getInstance().getPrepareDurationInMs());
        assertEquals(400, GameManager.getInstance().getTilesPerRegion());
        assertEquals(480, GameManager.getInstance().getWindowHeight());
        assertEquals(640, GameManager.getInstance().getWindowWidth());
    }
}
