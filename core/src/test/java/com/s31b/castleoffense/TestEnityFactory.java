/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense;

import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.player.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Dennis
 */
public class TestEnityFactory {
    @Test
    public void TestBuyEntity(){
        CoGame game = new CoGame(1);
        Player p1 = new Player(0, "", game);
        Defensive def = (Defensive)EntityFactory.buyEntity(EntityType.Tower_Blue, p1);
        Offensive off = (Offensive)EntityFactory.buyEntity(EntityType.Blue, p1);
    }
}
