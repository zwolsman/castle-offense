/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.EntityType;
import com.s31b.castleoffense.game.entity.Offensive;
import com.s31b.castleoffense.server.packets.EndWavePacket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nick
 */
public class EndWaveListener extends ClickListener{
    private CoGame game;
    
    public EndWaveListener(CoGame g){
        this.game = g;
    }
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
        //Clear the listview
        //TODO: add defensive to listview
        if (game.getPlayers() != null && game != null) {
            endWave();
        }
    }
    
     private void endWave() {
        List types = java.util.Arrays.asList(EntityType.values());
        EndWavePacket p = new EndWavePacket();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Offensive o : game.getCurrentWave().getOffensives()) {
            //int id = types.indexOf(EntityType.getTypeFromString(o.getType().toString()));
            int id = types.indexOf(o.getType());      
            ids.add(id);
        }
        p.entities = ids;

        Globals.client.send(p);
        //game.getCurrentWave().endWave(player.getId());
    }
}
