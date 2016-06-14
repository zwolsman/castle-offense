package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.server.packets.JoinGamePacket;
import com.s31b.castleoffense.ui.LobbyGroup;

/**
 *
 * @author Nick
 */
public class LobbyListener extends ClickListener {
    @Override
    public void clicked(InputEvent event, float x, float y) {
        // Get the ID and join the game
        if( getTapCount() == 2) { // put breakpoint here         
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            joinGame(((LobbyGroup)event.getListenerActor()).getGameId());
        }
    }
    
    private void joinGame(int id) {
        Globals.client.send(new JoinGamePacket(id));
    }
}
