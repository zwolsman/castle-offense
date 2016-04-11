package com.s31b.castleoffense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.ui.GameMenu;

/**
 * Game instance of our game (Castle of Clans) aka CoC
 *
 * @author fhict
 */
public class CastleOffense extends Game {

    Skin skin;
    CoGame game;
    OrthographicCamera camera;
    GameMenu menu;

    /**
     * Overriden method for creating the game.
     */
    @Override
    public void create() {
        game = new CoGame(0);
        this.setScreen(new GameMenu(this, game, game.getPlayerById(1)));
    }

    @Override
    public void render() {
        super.render();
        //menu.render(Gdx.graphics.getDeltaTime());
    }

    public void dispose() {
        Globals.SPRITE_BATCH.dispose();
    }
}
