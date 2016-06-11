package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.listeners.BackListener;

/**
 *
 * @author Nick
 */
public class LobbyScreen implements Screen{
    private Stage stage;
    private MainMenu mainMenu;
    private Image background;
    private imageButton buttonBack;
    private CoGame game;
    private Lobbyview lobbyview;
    
    public LobbyScreen(CoGame game, MainMenu mainMenu){
        this.game = game;
        this.mainMenu = mainMenu;
        this.create();
    }
     
    public void create(){
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        
        background = new Image(new Texture(Gdx.files.internal("GUIMenu/TMOTDbackground.jpg")));  
        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());

        lobbyview = new Lobbyview(500, 550, (Gdx.graphics.getWidth() / 2) - (500 / 2), (Gdx.graphics.getHeight()/ 2) - (550 / 2));
        lobbyview.setPaddingPercentage(15, 16, 10, 10);
        lobbyview.addString("lakjsdfklaf");
        lobbyview.addString("TESTING");
        lobbyview.addString("TESTING THE ");
        lobbyview.addString("asdfkjljadsklfja;dsf");
        lobbyview.addString("adfadslkf");
        
        buttonBack = new imageButton("buttonTerug");
        buttonBack.addListener(new BackListener(this.mainMenu));
        buttonBack.setSize(150, 60);
        buttonBack.setPosition(Gdx.graphics.getWidth() - 170, Gdx.graphics.getHeight() - 80);
                
        Gdx.input.setInputProcessor(stage);
        addActors();
    }
    
    private void addActors(){
        stage.addActor(background);
        stage.addActor(buttonBack);
        lobbyview.render(stage);
    }

    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
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
