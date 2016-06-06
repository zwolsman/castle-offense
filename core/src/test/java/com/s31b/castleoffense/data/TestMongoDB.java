/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.data;

import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.data.EntityDAO;
import com.s31b.castleoffense.data.MongoDB;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.GameManager;
import com.s31b.castleoffense.player.Player;
import java.rmi.RemoteException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

/**
 *
 * @author Dennis
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMongoDB {
    
    static MongoDB db;
    static Player p1;
    static EntityDAO off;
    static DefensiveDAO def;
    
    @BeforeClass
    public static void SetupMongo() throws RemoteException{
        db = new MongoDB();
        CoGame g = GameManager.getInstance().createGame();
        p1 = new Player(1, "Dennis", g);
        
        def = new DefensiveDAO("test", "test", "test", "test", 5, 10, 10);
        off = new OffensiveDAO("test", "test", "test", "test", 5, 100, 10, 15);
    }
    
    private int countAllEntitiesInDB(){
        return db.getAll(DefensiveDAO.class).size() + db.getAll(OffensiveDAO.class).size();
    }
    
    @Test
    public void TestaGetAll(){
        db.getAll(DefensiveDAO.class);
        db.getAll(OffensiveDAO.class);
    }
    
    @Test
    public void TestbInsert(){
        int expectedCount = countAllEntitiesInDB() + 2;
        db.insert(off);
        db.insert(def);
        
        //assertEquals(expectedCount, countAllEntitiesInDB());
    }
    
    @Test
    public void TestcUpdate(){
        db.update(off, off);
        db.update(def, def);
    }
    
    @Test
    public void TestdDelete(){
        /*int expectedCount = countAllEntitiesInDB() - 2;
        db.delete(off);
        db.delete(def);
        assertEquals(expectedCount, countAllEntitiesInDB());*/
    }
}
