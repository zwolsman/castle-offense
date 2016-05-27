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
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.KryoClient;
import com.s31b.castleoffense.server.packets.CreateGamePacket;
import com.s31b.castleoffense.server.packets.JoinGamePacket;
import com.s31b.castleoffense.server.packets.PlayerListPacket;
import java.util.Scanner;
import com.s31b.castleoffense.Globals;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 *
 * @author Nick
 */
public class MainMenu extends Listener implements Screen {
    private OrthographicCamera camera;
    private imageButton buttonPlay;
    private imageButton buttonInfo;
    private imageButton buttonQuit;
    private imageButton buttonJoin;
    private Image background;
    private Skin skin;
    private Stage stage;
    private CastleOffense co;
    private CoGame game;
    private Player player;

    public MainMenu(CastleOffense castleoffense, CoGame game){
        this.co = castleoffense;
        this.game = game;
        this.player = null;
        this.create();
    }
        
    public void create () {
        Globals.client = new KryoClient();
        Globals.client.getClient().addListener(this);
        Globals.client.connect();
        
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
                System.out.println("Creating game");
                startGame();    
            };
        });
        buttonJoin = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainJoin.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainJoinDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainJoinHover.png")));
        buttonJoin.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
               System.out.println("Enter the id of the game:");
               Scanner sc = new Scanner(System.in);
 
               int nextId = sc.nextInt();
               System.out.println("Trying to join game " + nextId);
               joinGame(nextId);
                
            };
        });
        buttonInfo = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainInfo.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainInfoDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainInfoHover.png")));
        buttonInfo.addListener( new ClickListener() {       
            @Override
            public void clicked(InputEvent event, float x, float y) {
               Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
               //co.setScreen(new InfoScreen(co, game));
               co.setScreen(new EndGameMenu(false, co, game));
            };
        });
        buttonQuit = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonMainQuit.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainQuitDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonMainQuitHover.png")));
        buttonQuit.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            };
        });

        Gdx.input.setInputProcessor(stage);

        /* Give main menu buttons their styling:
        horizontal pos: half of the screen horizontally
        Vertical pos: 35, 45, 55, 65 procent of the screen vertically */
        buttonPlay.setPosition((Gdx.graphics.getWidth() / 2) - (buttonPlay.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 55 + 30);
        buttonJoin.setPosition((Gdx.graphics.getWidth() / 2) - (buttonJoin.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 45 + 20);
        buttonInfo.setPosition((Gdx.graphics.getWidth() / 2) - (buttonInfo.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 35 + 10);
        buttonQuit.setPosition((Gdx.graphics.getWidth() / 2) - (buttonQuit.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 25);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false);
    }
    
    private void startGame() {
        Globals.client.send(new CreateGamePacket());
    }

    private void joinGame(int id) {
        Globals.client.send(new JoinGamePacket(id));
    }

    @Override
    public void received(Connection connection, Object obj) {
       if (obj instanceof PlayerListPacket) {
           PlayerListPacket packet = (PlayerListPacket) obj;
             for (String name : packet.players) {
               player = game.addPlayer(name);
            }
            System.out.println("Joined a game as " + player.getName());
            Gdx.app.postRunnable(new Runnable() {
                 @Override
               public void run() {
                    co.setScreen(new GameMenu(co, game, player));
                }
           });
       }
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
