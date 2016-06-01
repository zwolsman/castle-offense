
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
/**
 *
 * @author Nick
 */
public class TextDialog extends Dialog {
    
    public TextDialog(String title, String description, Skin skin) {
        super(title, skin);
        
        text(description);
        button("Ok");
    }
    
    @Override
    protected void result(Object object){
        super.result(object);
    }
    
}
