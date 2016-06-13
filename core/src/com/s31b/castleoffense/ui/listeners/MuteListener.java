package com.s31b.castleoffense.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.Settings;
import com.s31b.castleoffense.ui.imageButton;

/**
 *
 * @author Nick
 */
public class MuteListener extends ClickListener{
    imageButton mute;
    
    public MuteListener(imageButton buttonMute){
        this.mute = buttonMute;
        changeImage();
    }
    
    @Override
    public void clicked(InputEvent event, float x, float y) {
        Settings.toggleMute();
        changeImage();
    }
    
    private void changeImage(){
        if(Settings.isMuted()){
            mute.setImage("buttonMuted"); 
        }
        else if(!Settings.isMuted()){
            mute.setImage("buttonUnmuted");
        }
    }
    
}
