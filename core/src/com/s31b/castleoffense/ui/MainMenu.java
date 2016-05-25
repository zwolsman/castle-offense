/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.game.CoGame;

/**
 *
 * @author Nick
 */
public class MainMenu implements Screen {
    private OrthographicCamera camera;
    private imageButton buttonPlay;
    private imageButton buttonInfo;
    private imageButton buttonQuit;
    private imageButton buttonJoin;
    private Image background;
    private Cursor customCursor;
    private Skin skin;
    private Stage stage;
    private CastleOffense co;
    private CoGame game;
    private imageButton buttonPlayHovered;

    public MainMenu(CastleOffense castleoffense, CoGame game){
        this.co = castleoffense;
        this.game = game;
        this.create();
    }
        
    public void create () {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        background = new Image(new Texture(Gdx.files.internal("GUIMenu/TMOTDbackground.jpg")));
        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());
       
        buttonPlay = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainStart.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainStartDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainStartHover.png")));
        buttonPlay.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                co.setScreen(new GameMenu(co, game, game.getPlayerById(1))); 
            };
        });
        buttonJoin = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainJoin.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainJoinDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainJoinHover.png")));
        buttonJoin.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                
            };
        });
        buttonInfo = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainInfo.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainInfoDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainInfo.png")));
        buttonInfo.addListener( new ClickListener() {       
            @Override
            public void clicked(InputEvent event, float x, float y) {
               Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
               co.setScreen(new InfoScreen(co, game));
            };
        });
        buttonQuit = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainQuit.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainQuitDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainQuit.png")));
        buttonQuit.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            };
        });

        Gdx.input.setInputProcessor(stage);

        /* Give main menu buttons their styling:
        width: 200 px
        Height: 60 px
        horizontal pos: half of the screen horizontally
        Vertical pos: 35, 45, 55, 65 procent of the screen vertically */
        buttonPlay.setSize(200, 60);
        buttonJoin.setSize(200, 60);
        buttonInfo.setSize(200, 60);
        buttonQuit.setSize(200, 60);
        buttonPlay.setPosition((Gdx.graphics.getWidth() / 2) - (buttonPlay.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 65);
        buttonJoin.setPosition((Gdx.graphics.getWidth() / 2) - (buttonJoin.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 55);
        buttonInfo.setPosition((Gdx.graphics.getWidth() / 2) - (buttonInfo.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 45);
        buttonQuit.setPosition((Gdx.graphics.getWidth() / 2) - (buttonQuit.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 35);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.addActor(background);
        stage.addActor(buttonPlay);
        stage.addActor(buttonJoin);
        stage.addActor(buttonInfo);
        stage.addActor(buttonQuit);
        stage.act();
        stage.draw();
    }
    
     @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int i, int i1) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
