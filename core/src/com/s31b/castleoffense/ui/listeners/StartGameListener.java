package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.ui.TextDialog;

/**
 *
 * @author Nick
 */
public class StartGameListener extends ClickListener {
    private final Stage stage;
    private Skin skin;
    
    public StartGameListener(Stage stage){
        this.stage = stage;
    }
   
    @Override
    public void clicked(InputEvent event, float x, float y) {
         Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
         showDialog();
         System.out.println("Creating game");
    }
    
    private void showDialog(){
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        TextDialog td = new TextDialog("Spel naam", "Voer de naam van het spel in:", skin);
        td.show(stage);
        td.setPosition((stage.getWidth() / 2) - (td.getWidth() / 2), (stage.getHeight() / 2) - (td.getHeight() / 2));
    }
}
