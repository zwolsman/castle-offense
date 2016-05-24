package com.s31b.castleoffense.game;

import com.badlogic.gdx.Gdx;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.map.Map;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author Goos CoGame is short for Castle Offense Game this is because "Game"
 * is a default libGDX class
 */
public class CoGame {

    private int id;

    private int currentWaveId;

    private GameState state;
    private long startTime;
    private long endTime;
    private Map map;
    private List<Wave> waves;
    private List<Defensive> towers;
    private List<Player> players;
    private Player player1;
    private Player player2;
    private Timer timer;

    public CoGame(int id) {
        this.id = id;
        state = GameState.StartMenu;
        initializeGame();
    }

    /**
     * Gets a player instance if found, will return null if not found
     *
     * @param id the id of the player
     * @return
     */
    public Player getPlayerById(int id) {
        for (Player tempPlayer : players) {
            if (tempPlayer.getId() == id) {
                return tempPlayer;
            }
        }
        return null;
    }

    /**
     * Initializes the game
     */
    public void initializeGame() {
        startTime = System.currentTimeMillis();
        endTime = startTime + 60000;
        map = new Map();
        waves = new ArrayList<Wave>();
        towers = new ArrayList<Defensive>();
        player1 = new Player(1, "Speler 1", this);
        player2 = new Player(2, "Speler 2", this);
        players = Arrays.asList(player1, player2);
        EntityFactory.init();

        currentWaveId = 0;
        currentWaveId = nextWave().getNumber();
    }

    /**
     * Get the current wave that is being made by the player
     *
     * @return The wave
     */
    public Wave getCurrentWave() {
        for (Wave wave : waves) {
            if (wave.getNumber() == currentWaveId) {
                return wave;
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Starts a game
     */
    public void startGame() {
        this.state = GameState.InGame;
    }

    /**
     * Pauses a game
     */
    public void pauseGame() {
        this.state = GameState.Paused;
    }

    /**
     * Restarts a game
     */
    public void restartGame() {
        this.state = GameState.InGame;
        initializeGame(); // removes all data of current game and starts over in wave 1
    }

    /**
     * Ends a game
     */
    public void endGame() {
        this.state = GameState.Ended;
        System.out.println("Game ended!");
        System.exit(0);
    }
//TODO: implent multiple maps.
//    public void addMap(Map map) {
//        maps.add(map);
//    }

    /**
     * Gets the next wave and updates the id of the wave
     *
     * @return
     */
    public Wave nextWave() {
        currentWaveId++;
        Wave wave = new Wave(currentWaveId, this);
        waves.add(wave);
        return wave;
    }

    /**
     * Add a tower to the game ofr a specific player
     *
     * @param tower The tower that the player bought
     */
    public void addTower(Defensive tower) {
        towers.add(tower);
    }

    /**
     * Get all the towers of a game
     *
     * @return A list of towers
     */
    public List<Defensive> getAllTowers() {
        return Collections.unmodifiableList(towers);
    }

    public void update() {
        if (startTime < endTime) {
            for (Player player : players) {
                if (player.getCastle().getHitpoints() > 0) {
                    getCurrentWave().update();
                } else {
                    endGame();
                }
            }
        } else {
            for (Player player : players) {
                this.getCurrentWave().endWave(player.getId());
            }
        }
    }

    /**
     * Draws the game on the screen using the Global spritebatch
     */
    public void draw() {
        map.draw();
        getCurrentWave().draw();

        for (Defensive tower : getAllTowers()) {
            tower.draw();
        }
    }

    /**
     * Gets the current loaded map
     *
     * @return The map that the player sees
     */
    public Map getMap() {
        return this.map;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
    
    @Override
    public boolean equals(Object other){
        if (other == null){
                        return false;
        }
        if (other == this){
                        return true;
        }
        if (!(other instanceof CoGame)){
                        return false;
        }
        CoGame w = (CoGame)other;
        return 
            this.currentWaveId == w.currentWaveId &&
            this.id == w.id &&
            this.map == w.map &&
            this.player1 == w.player1 &&
            this.player2 == w.player2 &&
            this.players == w.players &&
            this.state == w.state &&
            this.towers == w.towers &&
            this.waves == w.waves;

    }
}
