/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.s31b.castleoffense.game.GameManager;

/**
 *
 * @author Nick
 */
public class mainMenu extends ApplicationAdapter {
        GameManager gm;
        OrthographicCamera camera;
        TextButton buttonPlay;
        TextButton buttonInfo;
        TextButton buttonQuit;
        Skin skin;
        Stage stage;
        
	@Override
	public void create () {
                stage = new Stage();
                gm = new GameManager();
                skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

                buttonPlay = new TextButton("Play", skin);
                buttonPlay.addListener( new ClickListener() {              
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        gm.createGame();
                    };
                });
                buttonInfo = new TextButton("Info", skin);
                buttonInfo.addListener( new ClickListener() {              
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                       // TODO: Go to the info page
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
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.addActor(buttonPlay);
                stage.addActor(buttonInfo);
                stage.addActor(buttonQuit);
                
                stage.draw();
	}
}
