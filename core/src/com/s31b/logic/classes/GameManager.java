package com.s31b.logic.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Goos
 */
public class GameManager {
    
    // GameManager is a singleton class, there can only ever be 1 instance of it.
    private static GameManager instance = null;
    
    private final int playerRowAmount = 2;
    private final int goldPerWave = 1000;
    private final int tilesPerRegion = 400;
    private final int prepareDurationInMs = 60000;
    private final int windowWidth = 640;
    private final int windowHeight = 480;
    
    private static int nextGameId;
    private final List<CoGame> allGames;
    
    public GameManager () {
        //instance = this; // to make sure instance won't be null again;
        nextGameId = 0;
        allGames = new ArrayList<CoGame>();
    }
    
    public static GameManager getInstance() {
        // if instance is null this is the first time the game manager gets called
        // create the ONE gamemanager instance
        if (instance == null) instance = new GameManager();
        return instance;
    }
    
    public CoGame createGame() {
        nextGameId++;
        CoGame game = new CoGame(nextGameId);
        allGames.add(game);
        return game;
    }

    public int getPlayerRowAmount() {
        return playerRowAmount;
    }

    public int getGoldPerWave() {
        return goldPerWave;
    }

    public int getTilesPerRegion() {
        return tilesPerRegion;
    }

    public int getPrepareDurationInMs() {
        return prepareDurationInMs;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
    
    public List<CoGame> getAllGames () {
        return Collections.unmodifiableList(allGames);
    }
}
