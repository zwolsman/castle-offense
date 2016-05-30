package com.s31b.castleoffense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.GameMenu;
import com.s31b.castleoffense.ui.MainMenu;

/**
 * Game instance of our game (Castle of Clans) aka CoC
 *
 * @author Marvin Zwolsman
 */
public class CastleOffense extends Game {

    Skin skin;
    CoGame game;
    OrthographicCamera camera;
    GameMenu menu;
    MainMenu mainMenu;

    /**
     * Overriden method for creating the game.
     */
    @Override
    public void create() {
        game = new CoGame(0);
        this.setScreen(new MainMenu(this, game));
    }

    @Override
    public void dispose() {
        TextureGlobals.SPRITE_BATCH.dispose();
    }
}
