package com.s31b.castleoffense.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.s31b.castleoffense.AudioPlayer;
import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.TextureFactory;
import com.s31b.castleoffense.game.CoGame;

/**
 *
 * @author Nick This menu will be shown when the game is finished (either win or
 * lose)
 */
public class EndGameMenu implements Screen {

    private Stage stage;
    private Image background;
    private Image stateImage;
    private imageButton buttonDone;
    private CoGame game;

    public EndGameMenu(Boolean winningState, CoGame game) {
        this.stateImage = new Image(TextureFactory.getTexture("GUIMenu/You" + (winningState ? "Win" : "Lose")));

        this.game = game;
        this.create();
    }

    public void create() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        background = new Image(new Texture(Gdx.files.internal("GUIMenu/TMOTDbackground.jpg")));
        background.setHeight(Gdx.graphics.getHeight());
        background.setWidth(Gdx.graphics.getWidth());

        stateImage.setPosition((Gdx.graphics.getWidth() / 2) - (stateImage.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 40);

        buttonDone = new imageButton("buttonTerug");
        buttonDone.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AudioPlayer.stop();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                CastleOffense.getInstance().setScreen(new MainMenu(game));
            }
        ;
        });
        buttonDone.setPosition((Gdx.graphics.getWidth() / 2) - (buttonDone.getWidth() / 2), Gdx.graphics.getHeight() / 100 * 20);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.addActor(background);
        stage.addActor(stateImage);
        stage.addActor(buttonDone);

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
