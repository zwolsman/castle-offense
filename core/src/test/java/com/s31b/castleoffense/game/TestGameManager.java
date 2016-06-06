/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.s31b.castleoffense.game.*;
import java.rmi.RemoteException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class TestGameManager {


    @Test
    public void TestStartingGame() throws RemoteException {
        int expected_games = GameManager.getInstance().getAllGames().size() + 1;
        CoGame cg = GameManager.getInstance().createGame();
        List<CoGame> games = GameManager.getInstance().getAllGames();
        assertEquals(expected_games, games.size());
        assertEquals(cg, games.get(games.size() - 1));
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
    
    @Test
    public void TestCreateGame() throws RemoteException{
        CoGame game = GameManager.getInstance().createGame();
        assertEquals(22, game.getId());
    }
}
