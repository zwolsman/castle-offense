package com.s31b.castleoffense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.s31b.castleoffense.game.CoGame;

public class CastleOffense extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	CoGame game;
        OrthographicCamera camera;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
                
               float w = Gdx.graphics.getWidth();
               float h = Gdx.graphics.getHeight();
                camera = new OrthographicCamera(w, h);
                camera.setToOrtho(false);
                //camera.setToOrtho(false,1,h / w);
                game = new CoGame(0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                
                game.draw(batch);
                batch.end();
                
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
}
