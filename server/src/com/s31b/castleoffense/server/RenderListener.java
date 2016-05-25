/*
 */
package com.s31b.castleoffense.server;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author fhict
 */
public class RenderListener implements ApplicationListener {

    @Override
    public void create() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render() {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("" + Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
