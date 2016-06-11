package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.s31b.castleoffense.ui.listeners.LobbyListener;

/**
 *
 * @author Nick
 */
public class Lobbyview extends Table {
    private Skin skin;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private Image background;
    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;

    public Lobbyview(int width, int height, int posX, int posY){
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        create();
        setPadding(40, 30, 10, 10);
    }
    
    private void create(){
        background = new Image(new Texture(Gdx.files.internal("GUIMenu/ListviewBackground.png")));
        background.setSize(width, height);
        background.setPosition(posX, posY);

        this.setSize(width - (paddingLeft + paddingRight), height - (paddingTop + paddingBottom));
        this.setPosition(posX + paddingLeft, posY + paddingBottom);
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        this.left().top();  
    }

    public void setPadding(int padTop, int PadBottom, int PadLeft, int PadRight){
        this.paddingTop = padTop;
        this.paddingBottom = PadBottom;
        this.paddingLeft = PadLeft;
        this.paddingRight = PadRight;
    }
    
    public void setPaddingPercentage(int percentageTop, int percentageBottom, int percentageLeft, int percentageRight){
        int padTop = (this.height / 100) * percentageTop;
        int padBottom = (this.height / 100) * percentageBottom;
        int padLeft = (this.width / 100) * percentageLeft;
        int padRight = (this.width / 100) * percentageRight;
        setPadding(padTop, padBottom, padLeft, padRight);
    }
    
    public void addString(String text){
        HorizontalGroup hg = new HorizontalGroup();
        Label l = new Label("testr", skin);
        l.setColor(Color.BLACK);
        hg.addActor(l);
        hg.addListener(new LobbyListener());
        this.add(hg).row();
    }
    
    public void render(Stage stage){
        stage.addActor(background);
        stage.addActor(this);
        
    }
     
}