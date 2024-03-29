package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.s31b.castleoffense.ui.listeners.HoverListener;

public class imageButton extends ImageButton
{   
    private ImageButtonStyle style;
    private Drawable draw_down;
    private Drawable draw_up;
    private Drawable draw_hover;
    private Texture texture_down;
    private Texture texture_up;
    private Texture texture_hover;
    private String extension = ".png";
    
    public imageButton(String button)
    {
        super(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("GUIMenu/" + button + ".png")))),
              new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("GUIMenu/" + button + "Down" + ".png")))));
        
        //Add the hovers to the button
        this.addListener(new HoverListener(this));
        
        style = new ImageButtonStyle();
        this.setStyle(style);
        
        create(button);
    }
    
    private void create(String button){
        texture_up = new Texture(Gdx.files.internal("GUIMenu/" + button + extension));
        texture_down = new Texture(Gdx.files.internal("GUIMenu/" + button + "Down" + extension));
        texture_hover = new Texture(Gdx.files.internal("GUIMenu/" + button + "Hover" + extension));
        
        draw_down = new Image(texture_down).getDrawable();
        draw_up = new Image(texture_up).getDrawable();
        draw_hover = new Image(texture_hover).getDrawable();
        
        style.up = draw_up;
        style.down = draw_down;
    }
    
    public void hoverOn(){
        style.up = draw_hover;
    }
    
    public void hoverOf(){
        style.up = draw_up;
    }
    
    public void setImage(String button){
        create(button);
    }
}