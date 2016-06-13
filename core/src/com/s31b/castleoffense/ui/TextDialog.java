
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.server.packets.CreateGamePacket;
/**
 *
 * @author Nick
 */
public class TextDialog extends Dialog {
    private TextField input;
    
    public TextDialog(String title, String description, Skin skin) {
        super(title, skin);    
        text(description);  
        input = new TextField("", skin);     
        button("Ok");
        this.addActor(input); 
    }
    
    @Override
    public Dialog show(Stage stage){
        Dialog dialog = super.show(stage);
        setSettings();
        return dialog;
    }
    
    private void setSettings(){
        this.setSize(500, 200);
        input.setY(40);
        input.setX((this.getWidth() / 2) - (input.getWidth() / 2));
    }
    
    @Override
    protected void result(Object object){
        startGame(input.getText());
    }
    
    private void startGame(String gameName) {
        Globals.client.send(new CreateGamePacket(gameName));
    }
}
