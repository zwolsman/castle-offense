/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.*;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.player.Player;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Dennis
 */
public class TestCoGame {
    
    CoGame game;

    @Before
    public void SetGame(){
        game = new CoGame(1);
    }
    
    @Test
    public void TestStartingGame() throws RemoteException{
        CoGame g = GameManager.getInstance().createGame();
        
        Wave currentWave = g.getCurrentWave();
        assertEquals(new Wave(1, g), currentWave);
        assertEquals(0, g.getAllTowers().size());
    }
    
    @Test
    public void TestStart(){
        game.startGame();
    }
    
    @Test
    public void TestPause(){
        game.pauseGame();
    }
    
    @Test
    public void TestRestart(){
        ///Set wave to 1
        game.restartGame();
        ///Next wave wave = 2
        game.nextWave();
        ///Check IF THE wave is 2
        assertEquals(new Wave(2, game), game.getCurrentWave());
        game.restartGame();
        ///Restarting game wave would be set to 1
        assertEquals(new Wave(1, game), game.getCurrentWave());
    }
    
    @Test
    public void TestGetId(){
        assertEquals(1, game.getId());
    }
    
    @Test
    public void TestGetWave(){
        game.restartGame();
        ///Check wave is 1
        /////Error : Wave is not resetted
        assertEquals(new Wave(1, game), game.getCurrentWave());
    }
    
    @Test
    public void TestNextWave(){
        game.nextWave();
        assertEquals(new Wave(2, game), game.getCurrentWave());
        
        game.nextWave();
        assertEquals(new Wave(3, game), game.getCurrentWave());
        
        game.nextWave();
        assertEquals(new Wave(4, game), game.getCurrentWave());
        
        game.nextWave();
        game.nextWave();
        game.nextWave();
        game.nextWave();
        game.nextWave();
        game.nextWave();
        assertEquals(new Wave(10, game), game.getCurrentWave());
    }
    
    @Test 
    public void TestAddTower(){
        
        int expected1 = 1;
        Defensive tower = (Defensive) EntityFactory.buyEntity(EntityType.Tower_Blue, game.getPlayerById(1));
        game.addTower(tower);
        assertEquals(expected1, game.getAllTowers().size());
        
        
        int expected2 = 2;
        game.addTower(tower);
        assertEquals(expected2, game.getAllTowers().size());
        
        int expected3 = 5;
        game.addTower(tower);
        game.addTower(tower);
        game.addTower(tower);
        assertEquals(expected3, game.getAllTowers().size());
    }
    
    @Test
    public void TestGetAllTowers(){
        Defensive tower = (Defensive) EntityFactory.buyEntity(EntityType.Tower_Blue, game.getPlayerById(1));
        game.addTower(tower);
        game.addTower(tower);
        game.addTower(tower);
        assertEquals(3, game.getAllTowers().size());
    }
    
    @Test
    public void TestEquals(){
        CoGame g1 = new CoGame(1);
        CoGame g2 = g1;
        CoGame g3 = new CoGame(1);
        
        Player p1 = new Player(0, "", g3);
        
        g3.initializeGame();
        g3.nextWave();
        g3.pauseGame();
        g3.addTower(new Defensive(EntityType.Blue, "", "", "", p1, 0, 0, 0));
        
        assertFalse("g3 is equals with g1", g1.equals(g3));
        assertTrue("g1 is not equals with g2",g1.equals(g2));
        assertFalse("g2 is equals with g3",g2.equals(g3));
        
        assertTrue("g1 is not equals with g1",g1.equals(g1));
        assertFalse("g1 is equals with null",g1.equals(null));
        assertFalse("g1 is not equals with player",g1.equals(p1));
    }

}
