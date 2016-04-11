/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.game;

import com.s31b.castleoffense.game.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Dennis
 */
public class TestCoGame {
    
    static CoGame game;

    @BeforeClass
    public static void SetGame(){
        game = GameManager.getInstance().createGame();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void TestStartingGame(){
        CoGame g = GameManager.getInstance().createGame();
        
        Wave currentWave = g.getCurrentWave();
        assertEquals(new Wave(1, g), currentWave);
        //assertEquals(0, g.getAllTowers().size());
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
    public void TestEndGame(){
        game.endGame();
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
        /*
        int expected1 = 1;
        game.addTower((Defensive)EntityFactory.buyEntity(EntityType.Defensive_Tower1, game.getPlayerById(1)));
        assertEquals(expected1, game.getAllTowers().size());
        
        
        int expected2 = 2;
        game.addTower((Defensive)EntityFactory.buyEntity(EntityType.Defensive_Tower1, game.getPlayerById(1)));
        assertEquals(expected2, game.getAllTowers().size());
        
        int expected3 = 5;
        game.addTower((Defensive)EntityFactory.buyEntity(EntityType.Defensive_Tower1, game.getPlayerById(1)));
        game.addTower((Defensive)EntityFactory.buyEntity(EntityType.Defensive_Tower1, game.getPlayerById(2)));
        game.addTower((Defensive)EntityFactory.buyEntity(EntityType.Defensive_Tower1, game.getPlayerById(1)));
        assertEquals(expected3, game.getAllTowers().size());*/
    }
    
    @Test
    public void TestGetAllTowers(){
        int expected1 = 0;
        assertEquals(expected1, game.getAllTowers().size());
    }
}
