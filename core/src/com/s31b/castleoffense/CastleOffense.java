package com.s31b.castleoffense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.GameMenu;
import com.s31b.castleoffense.ui.MainMenu;

public class CastleOffense extends Game {
    Skin skin;
    CoGame game;
    OrthographicCamera camera;
    GameMenu menu;
    MainMenu mainMenu;

    @Override
    public void create () {
        game = new CoGame(0);
        //this.setScreen(new GameMenu(this, game, game.getPlayerById(1)));
        this.setScreen(new MainMenu(this, game));
    }

    @Override
    public void render () {  
        super.render();
        //menu.render(Gdx.graphics.getDeltaTime());
    }
    
    public void dispose(){
        Globals.SPRITE_BATCH.dispose();
    }
}
