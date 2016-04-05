package com.s31b.castleoffense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.gameMenu;

public class CastleOffense extends ApplicationAdapter {
	SpriteBatch batch;
        Skin skin;
	CoGame game;
        OrthographicCamera camera;
        gameMenu menu;
        
	@Override
	public void create () {
                batch = new SpriteBatch();
                skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
                
                float w = Gdx.graphics.getWidth();
                float h = Gdx.graphics.getHeight();
                camera = new OrthographicCamera(w, h);
                camera.setToOrtho(false);
 
                game = new CoGame(0);
                menu = new gameMenu();
                menu.create();
                Gdx.input.setInputProcessor(menu.stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);
                batch.begin(); 
                game.draw();
                batch.end();
                menu.render();
	}
}
