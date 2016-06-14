/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

/**
 *
 * @author Nick
 */
public class LobbyGroup extends HorizontalGroup {
    private int gameId;
    
    public LobbyGroup(int gameId){
        super();
        this.gameId = gameId;
    }
    
    public int getGameId(){
        return gameId;
    }
}
