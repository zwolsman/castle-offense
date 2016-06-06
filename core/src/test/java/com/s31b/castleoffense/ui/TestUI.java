/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.ui.GameMenu;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import test.java.com.s31b.castleoffense.TestListener;

/**
 *
 * @author Dennis
 */
public class TestUI {
    CoGame g;
    Player p1;
    Player p2;
    CastleOffense co;
    
    @Before
    public void Setup() throws InterruptedException{
        HeadlessApplication ha = new HeadlessApplication(new TestListener());
        Thread.sleep(100);
        
        g = new CoGame(1);
        p1 = new Player(0, "", g);
        p2 = new Player(1, "", g);
        co = new CastleOffense();
    }
    
    @Test
    public void TestGameMenu(){
        //new GameMenu(co, g, p1);
    }
}
