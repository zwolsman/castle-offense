package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nick
 */
public class GameMenu implements Screen {

    private CastleOffense co;
    private CoGame game;
    private OrthographicCamera camera;
    public Stage stage;
    private Skin skin;
    private Batch batch;
    private Player player;
    private Player opponent;
    private List<DefensiveDAO> defList;
    private List<OffensiveDAO> offList;
    private List<OffensiveDAO> offPerWaveList;
    private int countOff = 0;
    private int countDefList = 0;
    private int countOffList = 0;
    private imageButton endWave;
    private imageButton surrender;
    private imageButton buyOff;
    private imageButton buyDef;
    private imageButton nextDef;
    private imageButton nextOff;
    private Image backgroundTabOff;
    private Image backgroundTabDef;
    private Image background;
    private Image menuBar;
    private Table main;
    private Label defLabel;
    private Label offLabel;
    private Label offPrice;
    private Label offHp;
    private Label offNumber;
    private Label offSpeed;
    private Label offDescription;
    private Label defPrice;
    private Label defDescription;
    private Label defDps;
    private Label defRange;
    private Label playerHp;
    private Label playerHpDesc;
    private Label playerName;
    private Label playerNameDesc;
    private Label playerGold;
    private Label playerGoldDesc;
    private Label castleHp;
    private Label castleHpDesc;
    
    public GameMenu(CastleOffense castleoffense, CoGame game, Player player) {
        this.co = castleoffense;
        this.game = game;
        this.player = player;
        this.defList = EntityFactory.getAllDefensives();
        this.offList = EntityFactory.getAllOffensives();
        this.offPerWaveList = new ArrayList<OffensiveDAO>();
        for(Player p : game.getPlayers()){
            if(p != player){
                this.opponent = p;
            }
        }
        
        this.create();
    }

    public void create() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        background = new Image(new Texture(Gdx.files.internal("GUIMenu/sky.png")));
        background.setWidth(Gdx.graphics.getWidth());

        menuBar = new Image(new Texture(Gdx.files.internal("GUIMenu/menuBar.png")));
        menuBar.setHeight(70);
        menuBar.setPosition(2, 450);

        playerNameDesc = new Label("Naam: ", skin);
        playerNameDesc.setColor(Color.BLACK);
        playerNameDesc.setPosition(15, 490);
        playerName = new Label(player.getName(), skin);
        playerName.setColor(Color.BLACK);
        playerName.setPosition(75, 490);
        
        playerHpDesc = new Label("Levenspunten: ", skin);
        playerHpDesc.setColor(Color.BLACK);
        playerHpDesc.setPosition(150, 490);
        playerHp = new Label(Integer.toString(player.getCastle().getHitpoints()), skin);
        playerHp.setColor(Color.BLACK);
        playerHp.setPosition(270, 490);
        
        playerGoldDesc = new Label("Geld: ", skin);
        playerGoldDesc.setColor(Color.BLACK);
        playerGoldDesc.setPosition(310, 490);
        playerGold = new Label(Integer.toString(player.getGold()), skin);
        playerGold.setColor(Color.BLACK);
        playerGold.setPosition(360, 490);
        
        castleHpDesc = new Label("Levenspunten tegenstander: ", skin);
        castleHpDesc.setColor(Color.BLACK);
        castleHpDesc.setPosition(400, 490);
        castleHp = new Label(Integer.toString(opponent.getCastle().getHitpoints()), skin);
        castleHp.setColor(Color.BLACK);
        castleHp.setPosition(620, 490);
  

        backgroundTabOff = new Image(new Texture(Gdx.files.internal("GUIMenu/board.png")));
        backgroundTabDef = new Image(new Texture(Gdx.files.internal("GUIMenu/board.png")));
        backgroundTabOff.setSize(500, 150);
        backgroundTabDef.setSize(500, 150);

        endWave = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonNextWave.png")), new Texture(Gdx.files.internal("GUIMenu/buttonNextWaveDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonNextWave.png")));
        endWave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                countOff = 0;
                offPerWaveList.clear();
                offNumber.setText(Integer.toString(countOff));
                game.getCurrentWave().endWave(player.getId());
                
                // Set the buttons disabled -- WORKS --
//                endWave.setTouchable(Touchable.disabled);
//                buyOff.setTouchable(Touchable.disabled);
//                buyDef.setTouchable(Touchable.disabled);
            }
        ;
        });

        surrender = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonSurrender.png")), new Texture(Gdx.files.internal("GUIMenu/buttonSurrenderDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonSurrender.png")));
        surrender.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.endGame();
            }
        ;
        });

        getTabPages();
        endWave.setSize(120, 70);
        endWave.setPosition(Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 130);
        surrender.setSize(100, 30);
        surrender.setPosition(Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 190);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);

        batch = TextureGlobals.SPRITE_BATCH;
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);

        game.getCurrentWave().endWave(2);

        Gdx.input.setInputProcessor(stage);
    }

    private void getTabPages() {
        main = new Table();
        main.setSize(500, 180);
        // Put the menu in the middle (Horizontal) - the width of the button endWave
        // Put the menu 200px form the top of the screen
        main.setPosition((Gdx.graphics.getWidth() / 2) - (main.getWidth() / 2 + endWave.getWidth() / 3), Gdx.graphics.getHeight() - 180);

        // Create the tab buttons
        HorizontalGroup group = new HorizontalGroup();
        final imageButton tab1 = new imageButton(new Texture(Gdx.files.internal("GUIMenu/tabPageButtonDef.png")), new Texture(Gdx.files.internal("GUIMenu/tabPageButtonDefDown.png")), new Texture(Gdx.files.internal("GUIMenu/tabPageButtonDef.png")));
        final imageButton tab2 = new imageButton(new Texture(Gdx.files.internal("GUIMenu/tabPageButtonOff.png")), new Texture(Gdx.files.internal("GUIMenu/tabPageButtonOffDown.png")), new Texture(Gdx.files.internal("GUIMenu/tabPageButtonOff.png")));
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
        ChangeListener tab_listener = new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
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

    private Table getDefensive() {
        Table contentDef = new Table();
        contentDef.addActor(backgroundTabDef);
        DefensiveDAO d = defList.get(0);

        defLabel = new Label(d.getName(), skin);
        defLabel.setPosition(60, 110);
        defLabel.setColor(Color.BLACK);

        Label defPriceDesc = new Label("Prijs: ", skin);
        defPriceDesc.setPosition(60, 90);
        defPriceDesc.setColor(Color.BLACK);
        defPrice = new Label(Float.toString(d.getPrice()), skin);
        defPrice.setPosition(170, 90);
        defPrice.setColor(Color.BLACK);

        Label defDescriptionDesc = new Label("Beschrijving: ", skin);
        defDescriptionDesc.setPosition(60, 70);
        defDescriptionDesc.setColor(Color.BLACK);
        defDescription = new Label(d.getDescr(), skin);
        defDescription.setPosition(170, 70);
        defDescription.setColor(Color.BLACK);

        Label defDpsDesc = new Label("Schade: ", skin);
        defDpsDesc.setPosition(60, 50);
        defDpsDesc.setColor(Color.BLACK);
        defDps = new Label(Integer.toString(d.getDPS()), skin);
        defDps.setPosition(170, 50);
        defDps.setColor(Color.BLACK);

        Label defRangeDesc = new Label("Bereik: ", skin);
        defRangeDesc.setPosition(60, 30);
        defRangeDesc.setColor(Color.BLACK);
        defRange = new Label(Integer.toString(d.getRange()), skin);
        defRange.setPosition(170, 30);
        defRange.setColor(Color.BLACK);

        buyDef = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonBuy.png")), new Texture(Gdx.files.internal("GUIMenu/buttonBuyDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonBuy.png")));
        buyDef.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tempCounter++;
                for(DefensiveDAO d : defList){
                    if(defLabel.getText().toString().equals(d.getName())){
                        Defensive entity = (Defensive)EntityFactory.buyEntity(EntityType.valueOf(d.getType()), player);
                        game.addTower(entity);
                        entity.setPosition(new Tile(tempCounter, 0));
                        System.out.println("Bought: " + entity.getName());
                        break;
                    }
                }
                
                playerGold.setText(Integer.toString(player.getGold()));
            }
        ;
        });
        buyDef.setPosition(350, 10);
        buyDef.setSize(100, 40);

        nextDef = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonNext.png")), new Texture(Gdx.files.internal("GUIMenu/buttonNextDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonNext.png")));
        nextDef.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                countDefList++;

                if (countDefList >= defList.size()) {
                    countDefList = 0;
                }

                defLabel.setText(defList.get(countDefList).getName());
                defPrice.setText(Integer.toString(defList.get(countDefList).getPrice()));
                defDescription.setText(defList.get(countDefList).getDescr());
                defDps.setText(Integer.toString(defList.get(countDefList).getDPS()));
                defRange.setText(Integer.toString(defList.get(countDefList).getRange()));
            }
        ;
        });
        nextDef.setPosition(350, 80);
        nextDef.setSize(100, 40);

        contentDef.addActor(nextDef);
        contentDef.addActor(buyDef);
        contentDef.addActor(defLabel);
        contentDef.addActor(defPrice);
        contentDef.addActor(defPriceDesc);
        contentDef.addActor(defDescription);
        contentDef.addActor(defDescriptionDesc);
        contentDef.addActor(defDps);
        contentDef.addActor(defDpsDesc);
        contentDef.addActor(defRange);
        contentDef.addActor(defRangeDesc);
        return contentDef;
    }

    private Table getOffensive() {
        Table contentOff = new Table();
        contentOff.addActor(backgroundTabOff);
        OffensiveDAO o = offList.get(0);

        offLabel = new Label(o.getName(), skin);
        offLabel.setPosition(60, 110);
        offLabel.setColor(Color.BLACK);

        Label offPriceDesc = new Label("Prijs: ", skin);
        offPriceDesc.setPosition(60, 90);
        offPriceDesc.setColor(Color.BLACK);
        offPrice = new Label(Float.toString(o.getPrice()), skin);
        offPrice.setPosition(170, 90);
        offPrice.setColor(Color.BLACK);;

        Label offDescriptionDesc = new Label("Beschrijving: ", skin);
        offDescriptionDesc.setPosition(60, 70);
        offDescriptionDesc.setColor(Color.BLACK);
        offDescription = new Label(o.getDescr(), skin);
        offDescription.setPosition(170, 70);
        offDescription.setColor(Color.BLACK);

        Label offSpeedDesc = new Label("Snelheid: ", skin);
        offSpeedDesc.setPosition(60, 50);
        offSpeedDesc.setColor(Color.BLACK);
        offSpeed = new Label("Test", skin);
        offSpeed = new Label(Integer.toString(o.getSpeed()), skin);
        offSpeed.setPosition(170, 50);
        offSpeed.setColor(Color.BLACK);

        Label offHpDesc = new Label("Levenspunten: ", skin);
        offHpDesc.setPosition(60, 30);
        offHpDesc.setColor(Color.BLACK);
        offHp = new Label(Integer.toString(o.getHP()), skin);
        offHp.setPosition(170, 30);
        offHp.setColor(Color.BLACK);

        Label offNumberDesc = new Label("Aantal: ", skin);
        offNumberDesc.setPosition(60, 10);
        offNumberDesc.setColor(Color.BLACK);
        offNumber = new Label("0", skin);
        offNumber.setPosition(170, 10);
        offNumber.setColor(Color.BLACK);

        buyOff = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonBuy.png")), new Texture(Gdx.files.internal("GUIMenu/buttonBuyDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonBuy.png")));
        buyOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {       
                countOff++;
                for(OffensiveDAO o : offList){
                    if(offLabel.getText().toString().equals(o.getName())){ 
                        offPerWaveList.add(o);
                        Offensive entity = (Offensive) EntityFactory.buyEntity(EntityType.valueOf(o.getType()), player);
                        game.getCurrentWave().addOffensive(entity);
                        System.out.println("Added to que: " + entity.getName());
                        break;
                    }
                }
                
                offNumber.setText(Integer.toString(countOff));
            }
        ;
        });
        buyOff.setPosition(350, 10);
        buyOff.setSize(100, 40);

        nextOff = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonNext.png")), new Texture(Gdx.files.internal("GUIMenu/buttonNextDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonNext.png")));
        nextOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                countOffList++;
 
                if(countOffList >= offList.size()){
                    countOffList = 0;
                }

                offLabel.setText(offList.get(countOffList).getName());
                offPrice.setText(Integer.toString(offList.get(countOffList).getPrice()));
                offDescription.setText(offList.get(countOffList).getDescr());
                offSpeed.setText(Integer.toString(offList.get(countOffList).getSpeed()));
                offHp.setText(Integer.toString(offList.get(countOffList).getHP()));
                //offNumber.setText(Integer.toString(offList.get(countOffList).getHP()));
            }
        ;
        });
        nextOff.setPosition(350, 80);
        nextOff.setSize(100, 40);

        contentOff.addActor(nextOff);
        contentOff.addActor(buyOff);
        contentOff.addActor(offLabel);
        contentOff.addActor(offPriceDesc);
        contentOff.addActor(offPrice);
        contentOff.addActor(offDescription);
        contentOff.addActor(offDescriptionDesc);
        contentOff.addActor(offSpeedDesc);
        contentOff.addActor(offSpeed);
        contentOff.addActor(offHpDesc);
        contentOff.addActor(offHp);
        contentOff.addActor(offNumberDesc);
        contentOff.addActor(offNumber);
        return contentOff;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.addActor(background);
        stage.addActor(menuBar);
        stage.addActor(main);
        stage.addActor(endWave);
        stage.addActor(surrender);
        stage.addActor(playerHpDesc);
        stage.addActor(playerNameDesc);
        stage.addActor(playerGoldDesc);
        stage.addActor(playerName);
        stage.addActor(playerHp);
        stage.addActor(playerGold);
        stage.addActor(castleHpDesc);
        stage.addActor(castleHp);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        playerGold.setText(Integer.toString(player.getGold()));
        playerHp.setText(Integer.toString(player.getCastle().getHitpoints()));
        castleHp.setText(Integer.toString(opponent.getCastle().getHitpoints()));
        
        TextureGlobals.SHAPE_RENDERER.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
            
        game.update();
        game.draw();
        
        batch.end();
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
 