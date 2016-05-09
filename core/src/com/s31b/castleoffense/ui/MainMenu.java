/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.GameManager;

/**
 *
 * @author Nick
 */
public class MainMenu implements Screen {
    private OrthographicCamera camera;
    private TextButton buttonPlay;
    private TextButton buttonInfo;
    private TextButton buttonQuit;
    private Skin skin;
    private Stage stage;
    private CastleOffense co;
    private CoGame game;

    public MainMenu(CastleOffense castleoffense, CoGame game){
        this.co = castleoffense;
        this.game = game;
        this.create();
    }
        
    public void create () {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //Image img = new Image(new Texture(Gdx.files.internal("GUIMenu/buttonMain.jpg")));
        
        buttonPlay = new TextButton("Play", skin);
        //buttonPlay.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("GUIMenu/buttonMain.jpg")))));
        buttonPlay.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                co.setScreen(new GameMenu(co, game, game.getPlayerById(1)));
                // set new screen
            };
        });
        buttonInfo = new TextButton("Info", skin);
        buttonInfo.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
               co.setScreen(new InfoScreen(co, game));
            };
        });
        buttonQuit = new TextButton("Quit", skin);
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
        Vertical pos: 30, 50, 70 procent of the screen vertically */
        buttonPlay.setSize(200, 60);
        buttonInfo.setSize(200, 60);
        buttonQuit.setSize(200, 60);
        buttonPlay.setPosition((Gdx.graphics.getWidth() / 2) - (buttonPlay.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 70);
        buttonInfo.setPosition((Gdx.graphics.getWidth() / 2) - (buttonPlay.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 50);
        buttonQuit.setPosition((Gdx.graphics.getWidth() / 2) - (buttonPlay.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 30);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.addActor(buttonPlay);
        stage.addActor(buttonInfo);
        stage.addActor(buttonQuit);

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
