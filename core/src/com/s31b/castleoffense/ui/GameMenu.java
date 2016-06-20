package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.s31b.castleoffense.AudioPlayer;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.data.DefensiveDAO;
import com.s31b.castleoffense.data.OffensiveDAO;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.*;
import com.s31b.castleoffense.server.packets.*;
import com.s31b.castleoffense.ui.listeners.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nick
 */
public class GameMenu extends Listener implements Screen {

    private CoGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;
    private Batch batch;
    private Player player;
    private Player opponent;
    private List<DefensiveDAO> defList;
    private List<OffensiveDAO> offList;
    private int countOff = 0;
    private int countDefList = 0;
    private int countOffList = 0;
    private imageButton endWave;
    private imageButton surrender;
    private imageButton buyOff;
    private imageButton buyDef;
    private imageButton nextDef;
    private imageButton nextOff;
    private imageButton buttonUnmute;
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
    private static Label feedback;
    private Listview offBought;

    private Defensive towerToPlace = null;

    public GameMenu(CoGame game, Player player) {
        if (player.getId() > 0) {
            opponent = game.getPlayerById((player.getId() + 1) % game.getPlayers().size());
            System.out.println("Opponent: " + opponent.getName());
        }
        this.game = game;
        this.player = player;
        this.defList = EntityFactory.getAllDefensives();
        this.offList = EntityFactory.getAllOffensives();
        for (Player p : game.getPlayers()) {
            if (p != player) {
                this.opponent = p;
            }
        }

        this.create();
        Globals.client.getClient().addListener(this);

    }

    public void create() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        AudioPlayer.loop("ambient.ogg", 0.9f);

        buttonUnmute = new imageButton("buttonUnmuted");
        buttonUnmute.addListener(new MuteListener(buttonUnmute));
        buttonUnmute.setSize(40, 40);
        buttonUnmute.setPosition(20, 650);

        background = new Image(new Texture(Gdx.files.internal("GUIMenu/sky.png")));
        background.setWidth(Gdx.graphics.getWidth());

        feedback = new Label("....", skin);
        feedback.setColor(Color.BLACK);
        feedback.setPosition(100, 575);

        offBought = new Listview(200, 170, Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight() - 180);

        endWave = new imageButton("buttonNextWave");
        endWave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (player != null && game != null) {
                    endWave();
                }
            }
        ;
        });

        surrender = new imageButton("buttonSurrender");
        surrender.addListener(new SurrenderListener(player.getId()));

        endWave.setSize(120, 70);
        endWave.setPosition(Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 110);
        surrender.setSize(100, 30);
        surrender.setPosition(Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 150);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);

        batch = TextureGlobals.SPRITE_BATCH;
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);

        setMenuBar(); // creates the menu bar for the player
        createEntityMenu(); // creates the menu for offensive and defensive entities.
        addActors(); // adds all the actors to the stage

        Gdx.input.setInputProcessor(stage);
    }

    private void addActors() {
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
        stage.addActor(feedback);
        stage.addActor(buttonUnmute);
        offBought.render(stage);
    }

    public static void setPlayerFeedback(String message) {
        if (feedback != null) {
            feedback.setText(message);
        }
    }

    private void setMenuBar() {
        menuBar = new Image(new Texture(Gdx.files.internal("GUIMenu/menuBar.png")));
        menuBar.setHeight(70);
        menuBar.setWidth(Gdx.graphics.getWidth());
        menuBar.setPosition(2, 450);

        // Labels created with dot istead of empty
        // This is because the .setText() method from libGdx changes the position if label is empty
        String playerNameString = ".";
        String playerHpString = ".";
        String playerGoldString = ".";
        String CastleHpString = ".";

        if (player != null && game != null) {
            playerNameString = player.getName();
        }

        playerNameDesc = new Label("Naam: ", skin);
        playerNameDesc.setColor(Color.BLACK);
        playerNameDesc.setPosition(15, 490);
        playerName = new Label(playerNameString, skin);
        playerName.setColor(Color.BLACK);
        playerName.setPosition(75, 490);

        playerHpDesc = new Label("Levenspunten: ", skin);
        playerHpDesc.setColor(Color.BLACK);
        playerHpDesc.setPosition(150, 490);
        playerHp = new Label(playerHpString, skin);
        playerHp.setColor(Color.BLACK);
        playerHp.setPosition(270, 490);

        playerGoldDesc = new Label("Geld: ", skin);
        playerGoldDesc.setColor(Color.BLACK);
        playerGoldDesc.setPosition(310, 490);
        playerGold = new Label(playerGoldString, skin);
        playerGold.setColor(Color.BLACK);
        playerGold.setPosition(360, 490);

        castleHpDesc = new Label("Levenspunten tegenstander: ", skin);
        castleHpDesc.setColor(Color.BLACK);
        castleHpDesc.setPosition(450, 490);
        castleHp = new Label(CastleHpString, skin);
        castleHp.setColor(Color.BLACK);
        castleHp.setPosition(670, 490);
    }

    private void createEntityMenu() {
        backgroundTabOff = new Image(new Texture(Gdx.files.internal("GUIMenu/board.png")));
        backgroundTabDef = new Image(new Texture(Gdx.files.internal("GUIMenu/board.png")));
        backgroundTabOff.setSize(500, 150);
        backgroundTabDef.setSize(500, 150);

        main = new Table();
        main.setSize(500, 180);
        // Put the menu in the middle (Horizontal) - the width of the button endWave
        // Put the menu 200px form the top of the screen
        main.setPosition((Gdx.graphics.getWidth() / 2) - (main.getWidth() / 2 + endWave.getWidth() / 3), Gdx.graphics.getHeight() - 180);

        // Create the tab buttons
        HorizontalGroup group = new HorizontalGroup();
        final imageButton tab1 = new imageButton("tabPageButtonDef");
        final imageButton tab2 = new imageButton("tabPageButtonOff");
        group.addActor(tab1);
        group.addActor(tab2);
        main.add(group);
        main.row();

        // Create the tab content. Using 2 methods for offensive and defensive
        Stack content = new Stack();
        final Table contentDef = fillWithDefensive();
        final Table contentOff = fillWithOffensive();

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

    private Table fillWithDefensive() {
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
        defDps = new Label(Integer.toString(d.getDamage()), skin);
        defDps.setPosition(170, 50);
        defDps.setColor(Color.BLACK);

        Label defRangeDesc = new Label("Bereik: ", skin);
        defRangeDesc.setPosition(60, 30);
        defRangeDesc.setColor(Color.BLACK);
        defRange = new Label(Integer.toString(d.getRange()), skin);
        defRange.setPosition(170, 30);
        defRange.setColor(Color.BLACK);

        buyDef = new imageButton("buttonBuy");
        buyDef.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buyDefensive();
            }
        ;
        });
        buyDef.setPosition(350, 10);
        buyDef.setSize(100, 40);

        nextDef = new imageButton("buttonNext");
        nextDef.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                countDefList++;
                if (countDefList >= defList.size()) {
                    countDefList = 0;
                }
                DefensiveDAO defDAO = defList.get(countDefList);
                setDefInfo(defDAO);
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

    private Table fillWithOffensive() {
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
        offHp = new Label(Double.toString(o.getHealthPoints()), skin);
        offHp.setPosition(170, 30);
        offHp.setColor(Color.BLACK);

        Label offNumberDesc = new Label("Aantal: ", skin);
        offNumberDesc.setPosition(60, 10);
        offNumberDesc.setColor(Color.BLACK);
        offNumber = new Label("0", skin);
        offNumber.setPosition(170, 10);
        offNumber.setColor(Color.BLACK);

        buyOff = new imageButton("buttonBuy");
        buyOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buyOffensive();
            }
        ;
        });
        buyOff.setPosition(350, 10);
        buyOff.setSize(100, 40);

        nextOff = new imageButton("buttonNext");
        nextOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                countOffList++;
                if (countOffList >= offList.size()) {
                    countOffList = 0;
                }
                setOffInfo(offList.get(countOffList));
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

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (player != null && game != null) {
            playerGold.setText(Integer.toString(player.getGold()));
            playerHp.setText(Integer.toString(player.getCastle().getHitpoints()));

            if (opponent != null) {
                castleHp.setText(Integer.toString(opponent.getCastle().getHitpoints()).trim());
            }
        }

        TextureGlobals.SHAPE_RENDERER.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        if (game != null) {
            game.update();
            game.draw();
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && towerToPlace != null && game.getMap().getSelectedTile() != null) {
            placeTower();
        } else if (towerToPlace != null) {
            drawGhostTower();
        }
        batch.end();
    }

    private void setDefInfo(DefensiveDAO defDAO) {
        defLabel.setText(defDAO.getName());
        defPrice.setText(Integer.toString(defDAO.getPrice()));
        defDescription.setText(defDAO.getDescr());
        defDps.setText(Integer.toString(defDAO.getDamage()));
        defRange.setText(Integer.toString(defDAO.getRange()));
    }

    private void setOffInfo(OffensiveDAO off) {
        offLabel.setText(off.getName());
        offPrice.setText(Float.toString(off.getPrice()));
        offDescription.setText(off.getDescr());
        offSpeed.setText(Integer.toString(off.getSpeed()));
        offHp.setText(Double.toString(off.getHealthPoints()));
        updateBoughtCount(EntityType.valueOf(off.getType()));
    }

    private void updateBoughtCount(EntityType offType) {
        int countOffBought = 0;

        for (Offensive tempOff : game.getCurrentWave().getOffensives()) {
            if (tempOff.getType().equals(offType)) {
                countOffBought++;
            }
        }
        offNumber.setText(Integer.toString(countOffBought));
    }

    private void buyOffensive() {
        if (game != null && player != null) {
            for (OffensiveDAO o : offList) {
                if (offLabel.getText().toString().equals(o.getName())) {
                    Offensive tempO = player.buyOffensiveEntity(EntityType.valueOf(o.getType()));
                    if (tempO != null) {
                        offBought.addString(tempO.getName());
                        System.out.println("Added to queue: " + o.getName());
                    } else {
                        StatusUpdate.log("De " + o.getName() + " kan niet gekocht worden");
                    }
                    break;
                }
            }
        }
    }

    private void buyDefensive() {
        if (player != null && game != null) {
            for (DefensiveDAO d : defList) {
                if (defLabel.getText().toString().equals(d.getName())) {
                    towerToPlace = player.buyDefensiveEntity(EntityType.valueOf(d.getType()));
                    if (towerToPlace != null) {
                        System.out.println("Bought: " + towerToPlace.getName());
                    } else {
                        StatusUpdate.log("De " + d.getName() + " kan niet gekocht worden");
                    }
                    break;
                }
            }
            playerGold.setText(Integer.toString(player.getGold()));
        }
    }

    private void placeTower() {
        Tile t = game.getMap().getSelectedTile();
        Globals.client.send(new BuyTowerPacket(t.getX(), t.getY(), towerToPlace.getType().name()));
        System.out.println("Send packet");
        towerToPlace = null;
    }

    private void endWave() {
        List types = java.util.Arrays.asList(EntityType.values());
        EndWavePacket p = new EndWavePacket();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Offensive o : player.getOffensives()) {
            int id = types.indexOf(o.getType());
            ids.add(id);
        }
        p.entities = ids;

        Globals.client.send(p);
        offNumber.setText("0");
        offBought.clearChildren();
    }

    @Override
    public void received(Connection c, Object obj) {
        System.out.println("Received packet");
        System.out.println(obj);
        if (obj instanceof BoughtTowerPacket) {
            BoughtTowerPacket packet = (BoughtTowerPacket) obj;

            Defensive tower = (Defensive) EntityFactory.buyEntity(EntityType.getTypeFromString(packet.name), game.getPlayerById(packet.pid));
            tower.setPosition(new Tile(packet.x, packet.y));
            game.addTower(tower);
        }

        if (obj instanceof PlayerListPacket) {
            PlayerListPacket packet = (PlayerListPacket) obj;
            for (String name : packet.players) {

                Player p = game.getPlayerByName(name);
                if (p == null) {
                    System.out.println("Got opponent, name: " + name);
                    opponent = game.addPlayer(name);
                }
            }
        }

        if (obj instanceof EndWavePacket) {
            EndWavePacket packet = (EndWavePacket) obj;
            Player p = game.getPlayerById(packet.pid);
            for (Integer i : packet.entities) {
                EntityType type = EntityType.values()[i];
                game.getCurrentWave().addOffensive((Offensive) EntityFactory.buyEntity(type, p));
            }
            game.getCurrentWave().endWave();
        }

        if (obj instanceof WinGamePacket) {
            WinGamePacket packet = (WinGamePacket) obj;
            Gdx.app.postRunnable(() -> {
                game.endGame(packet.loserid != player.getId());
            });
        }
    }

    private void drawGhostTower() {
        Tile t = game.getMap().getSelectedTile();
        if (t != null) {
            TextureGlobals.SPRITE_BATCH.draw(
                    TextureFactory.getTexture(towerToPlace.getSprite()),
                    t.getX(true),
                    t.getY(true),
                    Globals.TILE_WIDTH, Globals.TILE_HEIGHT
            );
        }
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
