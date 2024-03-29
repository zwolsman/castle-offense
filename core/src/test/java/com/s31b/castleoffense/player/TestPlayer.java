/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.player;

import com.badlogic.gdx.Game;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.GameManager;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.map.TileType;
import com.s31b.castleoffense.player.Castle;
import com.s31b.castleoffense.player.Player;
import java.rmi.RemoteException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
public class TestPlayer {
    CoGame g;
    Player p1;
    Player p2;
    Player p3;
    Player p4;
    Player p5;

    @Before
    public void SetUpPlayers(){
        GameManager gm = GameManager.getInstance();
        g = new CoGame(1);

        p1 = new Player(0, "Test gebruiker", g);
        p2 = new Player(1, "", g);
        p3 = new Player(1, "999999999", g);
        p4 = new Player(0, "kwekfkwekfkwekfewkwekwke", g);
        p5 = new Player(0, "?#^(^#83", g);
    }

    @Test
    public void TestCreatePlayer(){
        assertEquals(0, p1.getId());
        assertEquals("Test gebruiker", p1.getName());
        assertEquals(g, p1.getGame());

        assertEquals(1, p2.getId());
        assertEquals("", p2.getName());
        assertEquals(g, p2.getGame());

        assertEquals(1, p3.getId());
        assertEquals("999999999", p3.getName());
        assertEquals(g, p3.getGame());

        assertEquals(0, p4.getId());
        assertEquals("kwekfkwekfkwekfewkwekwke", p4.getName());
        assertEquals(g, p4.getGame());

        assertEquals(0, p5.getId());
        assertEquals("?#^(^#83", p5.getName());
        assertEquals(g, p5.getGame());
    }

    @Test
    public void TestGetCastle(){
        assertEquals(new Castle(p1), p1.getCastle());
        assertEquals(new Castle(p2), p2.getCastle());
    }

    @Test
    public void TestGetGold(){
        assertEquals(100, p1.getGold());
        assertEquals(100, p2.getGold());
    }

    @Test
    public void TestGetOffensiveSpawnPosition(){
        assertEquals("Player 1 spawn pos", new Tile(0, 10, TileType.Castle, g.getMap()), p1.getOffensiveSpawnPosition());
        assertEquals("Player 2 spawn pos", new Tile(28, 11, TileType.Castle, g.getMap()), p2.getOffensiveSpawnPosition());
    }

    @Test
    public void TestGetPoints(){
        assertEquals(0, p1.getPoints());
        assertEquals(0, p2.getPoints());
    }

    @Test
    public void TestBuyDefensiveTower() throws RemoteException{
        float EntityPrice = EntityFactory.getEntityPriceByType(EntityType.Tower_Blue);

        ///Not enough money it cost 200 but you have 100
        assertNull("You can buy towers without money.", p1.buyDefensiveEntity(EntityType.Tower_Blue));

        p1.addGold(1000000000);

        ////Add tower on location 5,5
        float resultPrice1 = p1.getGold() - EntityPrice;
        assertNotNull("Buying tower failed.", p1.buyDefensiveEntity(EntityType.Tower_Blue));
        assertEquals("Bought tower but money is not correct.", resultPrice1, p1.getGold(), 0);
        assertEquals("Games has not added the tower.", 1, g.getAllTowers().size());

        ///Can't add tower location 5,5 is already a tower
        //assertNull("Tower location already exists but tower added", p1.buyDefensiveEntity(EntityType.Tower_Blue));

         ////Add tower on location 5,4
         float resultPrice2 = p1.getGold() - EntityPrice;
        assertNotNull("Buying tower failed.", p1.buyDefensiveEntity(EntityType.Tower_Blue));
        assertEquals("Bought tower but money is not correct.", resultPrice2, p1.getGold(), 0);
        assertEquals("Game has not added the tower.", 2, g.getAllTowers().size());
    }

    @Test
    public void TestBuyOffensiveNpc(){
        p1.addGold(3000);
        float EntityPrice = EntityFactory.getEntityPriceByType(EntityType.Police);

        float resultPrice1 = p1.getGold() - EntityPrice;
        assertNotNull("Buying offensive failed.", p1.buyOffensiveEntity(EntityType.Police));
        assertEquals("Bought offensive but money is not correct.", resultPrice1, p1.getGold(), 0);
        //assertEquals("Game has not added the tower.",1, g.getAllTowers().size());

        float resultPrice2 = p1.getGold() - EntityPrice;
        assertNotNull("Buying offensive failed.", p1.buyOffensiveEntity(EntityType.Police));
        assertEquals("Bought offensive but money is not correct.", resultPrice2, p1.getGold(), 0);
        //assertEquals("Game has not added the tower.",2, g.getAllTowers().size());

        ///Deletes all money
        for(int i = 0; i <= 100; i++){
            p1.buyOffensiveEntity(EntityType.Police);
        }

        ///Not enough money it cost 5 but you have 0
        assertNull("You can buy towers without money.", p1.buyOffensiveEntity(EntityType.Police));
    }

    @Test
    public void TestSetName(){
        p1.setName("test1");
        assertEquals("test1", p1.getName());

        p1.setName("");
        assertEquals("", p1.getName());

        p1.setName("23236");
        assertEquals("23236", p1.getName());
    }

    @Test
    public void TestAddPoints(){
        assertEquals(0, p1.getPoints());

        p1.addPoints(0);
        assertEquals(0, p1.getPoints());

        p1.addPoints(-1);
        assertEquals(-1, p1.getPoints());

        p1.addPoints(1);
        assertEquals(0, p1.getPoints());
    }

    @Test
    public void TestAddGold(){
        assertEquals(100, p1.getGold());

        p1.addGold(0);
        assertEquals(100, p1.getGold());

        p1.addGold(-1);
        assertEquals(99, p1.getGold());

        p1.addGold(1);
        assertEquals(100, p1.getGold());
    }
}
