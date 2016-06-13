package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.server.packets.GameListPacket;
import com.s31b.castleoffense.server.packets.RequestGameListPacket;
import com.s31b.castleoffense.ui.listeners.BackListener;

/**
 *
 * @author Nick
 */
public class LobbyScreen extends Listener implements Screen {

    private Stage stage;
    private final MainMenu mainMenu;
    private Image background;
    private imageButton buttonBack;
    private final CoGame game;
    private Lobbyview lobbyview;
    private GameListPacket lastPacket;

    public LobbyScreen(CoGame game, MainMenu mainMenu) {
        this.game = game;
        this.mainMenu = mainMenu;
        this.create();
    }

    public void create() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        background = new Image(new Texture(Gdx.files.internal("GUIMenu/TMOTDbackground.jpg")));
        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());

        lobbyview = new Lobbyview(500, 550, (Gdx.graphics.getWidth() / 2) - (500 / 2), (Gdx.graphics.getHeight() / 2) - (550 / 2));
        lobbyview.setPaddingPercentage(15, 16, 10, 10);

        buttonBack = new imageButton("buttonTerug");
        buttonBack.addListener(new BackListener(this.mainMenu));
        buttonBack.setSize(150, 60);
        buttonBack.setPosition(Gdx.graphics.getWidth() - 170, Gdx.graphics.getHeight() - 80);

        Gdx.input.setInputProcessor(stage);
        addActors();
        Globals.client.getClient().addListener(this);
        requestGames();
    }

    private void addActors() {
        stage.addActor(background);
        stage.addActor(buttonBack);
        lobbyview.render(stage);
    }

    float timePassed = 0;

    @Override
    public void render(float delta) {
        timePassed += Gdx.graphics.getDeltaTime();

        if (timePassed > 60) {
            requestGames();
            timePassed = 0;
        }
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

    }

    private void requestGames() {
        lobbyview.clearChildren();
        Globals.client.send(new RequestGameListPacket());
    }

    @Override
    public void received(Connection connection, Object obj) {
        if (obj instanceof GameListPacket) {
            lastPacket = (GameListPacket) obj;
            for (String name : lastPacket.games) {
                lobbyview.addString(name);
            }
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
