/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nick
 */
public class BuyOffListener extends ClickListener {
    private List<OffensiveDAO> offensives;
    private List<OffensiveDAO> offPerWaveList = new ArrayList<OffensiveDAO>();
    private String offensiveType;
    private CoGame game;
    private Player player;
    
    public BuyOffListener(CoGame g, String offensiveString, Player p){
        this.player = p;
        this.offensiveType = offensiveString;
        this.offensives = EntityFactory.getAllOffensives();
        this.game = g;
    }
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
            for (OffensiveDAO o : offensives) {
                if (offensiveType.equals(o.getName())) {    
                    Offensive off = (Offensive)EntityFactory.buyEntity(EntityType.valueOf(o.getType()), player);    
                    game.getCurrentWave().addOffensive(off);
                    
                    System.out.println("Added to que: " + o.getName());
                    break;
                }
            }
            
            //gameStage.OffNumber.setText(Integer.toString(offPerWaveList.size()));
    }
}
