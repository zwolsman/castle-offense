/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.*;

/**
 *
 * @author Nick
 */
public class GameMenu implements Screen {
    private CastleOffense co;
    private CoGame game;
    private OrthographicCamera camera;
    private TextButton endWave;
    private TextButton surrender;
    private TextButton buyOff;
    private TextButton buyDef;
    private Skin skin;
    public Stage stage;
    private Table main;
    private Label defLabel;
    private Label offLabel;
    private Batch batch;
    private Player player;
        
    public GameMenu(CastleOffense castleoffense, CoGame game, Player player) {
        this.co = castleoffense;
        this.game = game;
        this.player = player;
        this.create();
    }
    
    public void create () {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        endWave = new TextButton("End wave", skin);
        endWave.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getCurrentWave().endWave(player.getId());
            };
        });

        surrender = new TextButton("surrender", skin);
        surrender.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.endGame();
                // TODO: surrender (Go back to main menu)
            };
        });

        getTabPages();
        endWave.setPosition(600, 500);
        surrender.setPosition(505, 500);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);

        batch = Globals.SPRITE_BATCH;
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);

        game.getCurrentWave().endWave(2);             
        
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.addActor(main);
        stage.addActor(endWave);
        stage.addActor(surrender);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
       
        batch.begin();
        batch.setProjectionMatrix(camera.combined);          
            
        game.update();
        game.draw();
        
        batch.end();
    }
    
    private void getTabPages(){
        main = new Table();
        main.setSize(500, 150);
        main.setPosition(0, 490);

        // Create the tab buttons
        HorizontalGroup group = new HorizontalGroup();      
        final Button tab1 = new TextButton("Defensive", skin, "toggle");
        final Button tab2 = new TextButton("Offensive", skin, "toggle");
        group.addActor(tab1);
        group.addActor(tab2);
        main.add(group);
        main.row();

        // Create the tab content. Just using images here for simplicity.
        Stack content = new Stack();
        final Table contentDef = getDefensive();
        final Table contentOff = getOffensive();

        content.addActor(contentDef);
        content.addActor(contentOff);

        main.add(content).expand().fill();

        // Listen to changes in the tab button checked states
        // Set visibility of the tab content to match the checked state
        ChangeListener tab_listener = new ChangeListener(){
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                contentDef.setVisible(tab1.isChecked());
                contentOff.setVisible(tab2.isChecked());
            }
        };
        tab1.addListener(tab_listener);
        tab2.addListener(tab_listener);

        // Let only one tab button be checked at a time
        ButtonGroup tabs = new ButtonGroup();
        tabs.setMinCheckCount(1);
        tabs.setMaxCheckCount(1);
        tabs.add(tab1);
        tabs.add(tab2);
    }
    
    private int tempCounter = 0;
    
    private Table getDefensive(){
        Table contentDef = new Table();
        defLabel = new Label("Tower", skin);
        defLabel.setPosition(main.getWidth() / 3, 80);

        buyDef = new TextButton("Buy", skin);
        buyDef.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                // TODO: this should be player.BuyDefensiveEntity()
                Defensive entity = (Defensive)EntityFactory.buyEntity(EntityType.Defensive_Tower1, player);
                tempCounter++;
                entity.setPosition(new Tile(tempCounter, 0));
                game.addTower(entity);
            };
        });
        buyDef.setPosition(370, 10);
        buyDef.setSize(100, 40);
        
        contentDef.background(skin.newDrawable("white", Color.GRAY)); 
        contentDef.addActor(buyDef);
        contentDef.addActor(defLabel);
        return contentDef;
    }

    private Table getOffensive(){
        Table contentOff = new Table();
        offLabel = new Label("Zombie", skin);
        offLabel.setPosition(50, 50);
        offLabel.setPosition(main.getWidth() / 3, 80);

        buyOff = new TextButton("Buy", skin);
        buyOff.addListener( new ClickListener() {              
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                // TODO: this should be player.BuyOffensiveEntity()
                Offensive entity = (Offensive)EntityFactory.buyEntity(EntityType.Offensive_Npc1, player);
                game.getCurrentWave().addOffensive(entity);
                // TODO: Add offensive to some kind of listview
            };
        });
        buyOff.setPosition(370, 10);
        buyOff.setSize(100, 40);

        contentOff.background(skin.newDrawable("white", Color.GRAY)); 
        contentOff.addActor(buyOff);
        contentOff.addActor(offLabel);
        return contentOff;
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
