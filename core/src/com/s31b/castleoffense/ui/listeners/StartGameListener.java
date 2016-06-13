package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.server.packets.CreateGamePacket;
import com.s31b.castleoffense.ui.TextDialog;

/**
 *
 * @author Nick
 */
public class StartGameListener extends ClickListener {
    Skin skin;
    Stage stage;
    
    public StartGameListener(Stage stage){
        this.stage = stage;
    }
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
         Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
         showDialog();
         System.out.println("Creating game");
//         startGame();
    }
    
//    private void startGame() {
//        Globals.client.send(new CreateGamePacket());
//    }

    private void showDialog(){
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        TextDialog td = new TextDialog("Spel naam", "Voer de naam van het spel in:", skin);
        td.show(stage);
    }
}
