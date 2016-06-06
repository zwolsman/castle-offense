package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.server.packets.WinGamePacket;

/**
 *
 * @author Nick
 */
public class SurrenderListener extends ClickListener {
    
    private final int loser;
    
    public SurrenderListener(int loser){
        this.loser = loser;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Globals.client.send(new WinGamePacket(loser));
    }
}
