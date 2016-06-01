package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.listeners.BackListener;

/**
 *
 * @author Nick
 */
public class LobbyScreen implements Screen{
    private OrthographicCamera camera;
    private Stage stage;
    private Image background;
    private Image infoBody;
    private imageButton buttonBack;
    private CastleOffense co;
    private CoGame game;
    private ListView listView;
    private TextDialog td;
    
    public LobbyScreen(CastleOffense castleoffense, CoGame game){
        this.co = castleoffense;
        this.game = game;
        this.create();
    }
     
    public void create(){
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
       
        background = new Image(new Texture(Gdx.files.internal("GUIMenu/TMOTDbackground.jpg")));  
        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());
        
        listView = new ListView(500, 550, (Gdx.graphics.getWidth() / 2) - (500 / 2), (Gdx.graphics.getHeight()/ 2) - (550 / 2));
        listView.setPaddingPercentage(22, 16, 10, 10);
        listView.addString("lakjsdfklaf");
        listView.addString("TESTING");
        listView.addString("TESTING THE ");
        listView.addString("asdfkjljadsklfja;dsf");
        listView.addString("adfadslkf");
        listView.addString("kldsjfiejiiiii");
        listView.addString("aaa");
         listView.addString("lakjsdfklaf");
        listView.addString("TESTING");
        listView.addString("TESTING THE ");
        listView.addString("asdfkjljadsklfja;dsf");
        listView.addString("adfadslkf");
        listView.addString("kldsjfiejiiiii");
        listView.addString("aaa");
         listView.addString("lakjsdfklaf");
        listView.addString("TESTING");
        listView.addString("TESTING THE ");
        listView.addString("asdfkjljadsklfja;dsf");
        listView.addString("adfadslkf");
        listView.addString("kldsjfiejiiiii");
        listView.addString("aaa");
         listView.addString("lakjsdfklaf");
        listView.addString("TESTING");
        listView.addString("TESTING THE ");
        listView.addString("asdfkjljadsklfja;dsf");
        listView.addString("adfadslkf");
        listView.addString("kldsjfiejiiiii");
        listView.addString("aaa");
         listView.addString("lakjsdfklaf");
        listView.addString("TESTING");
        listView.addString("TESTING THE ");
        listView.addString("asdfkjljadsklfja;dsf");
        listView.addString("adfadslkf");
        listView.addString("kldsjfiejiiiii");
        listView.addString("aaa");
        

        buttonBack = new imageButton(new Texture(Gdx.files.internal("GUIMenu/buttonTerug.png")), new Texture(Gdx.files.internal("GUIMenu/buttonTerugDown.png")), new Texture(Gdx.files.internal("GUIMenu/buttonTerugHover.png")));
        buttonBack.addListener(new BackListener(co, game)); 
        buttonBack.setSize(150, 60);
        buttonBack.setPosition(Gdx.graphics.getWidth() - 170, Gdx.graphics.getHeight() - 80);
                
        Gdx.input.setInputProcessor(stage);
    }
     
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.addActor(background);
        stage.addActor(buttonBack);
        listView.render(stage);

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
