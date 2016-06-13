package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.ui.imageButton;

/**
 *
 * @author Nick
 */
public class MuteListener extends ClickListener{
    imageButton mute;
    Boolean muted = false;
    
    public MuteListener(imageButton buttonMute){
        this.mute = buttonMute;
    }
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(muted == true){
            mute.setImage("buttonMuted");
            muted = false;
        }
        else if(muted == false){
            mute.setImage("buttonUnmuted");
            muted = true;
        }
    }
    
}
