package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 *
 * @author Nick
 */
public class HoverListener extends InputListener {
   private Cursor customCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("hand-pointer.png")), 0, 0);
   private imageButton imgButton;
   
   public HoverListener(imageButton button){
       imgButton = button;
   }
   
   public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
      if (pointer == -1){
          imgButton.hoverOn();
          Gdx.graphics.setCursor(customCursor);
          customCursor.dispose();
      }
   }

   public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
      if (pointer == -1){
          Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
          imgButton.hoverOf();
      }
   }
}

