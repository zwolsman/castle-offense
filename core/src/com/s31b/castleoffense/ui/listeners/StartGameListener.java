package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.server.packets.CreateGamePacket;

/**
 *
 * @author Nick
 */
public class StartGameListener extends ClickListener {
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
         Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
         System.out.println("Creating game");
         startGame();
    }
    
    private void startGame() {
        Globals.client.send(new CreateGamePacket());
    }
}
