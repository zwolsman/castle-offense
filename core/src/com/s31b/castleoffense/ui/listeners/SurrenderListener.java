/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.EndGameMenu;

/**
 *
 * @author Nick
 */
public class SurrenderListener extends ClickListener {
    private CastleOffense co;
    private CoGame game;
    
    public SurrenderListener(CastleOffense co, CoGame g){
        this.co = co;
        this.game = g;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        co.setScreen(new EndGameMenu(false, co, game));
    }
}
