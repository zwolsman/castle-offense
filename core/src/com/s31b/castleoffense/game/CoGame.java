package com.s31b.castleoffense.game;

import com.s31b.castleoffense.CastleOffense;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.map.Map;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.server.packets.WinGamePacket;
import com.s31b.castleoffense.ui.EndGameMenu;
import com.s31b.castleoffense.ui.GameMenu;
import com.s31b.castleoffense.ui.StatusUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Goos CoGame is short for Castle Offense Game this is because "Game"
 * is a default libGDX class
 */
public class CoGame {

    private final int id;

    private int currentWaveId;

    private GameState state;
    private long startTime;
    private long endTime;
    private Map map;
    private List<Wave> waves;
    private List<Defensive> towers;
    private final List<Player> players = new ArrayList<>();

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

    public Player getPlayerByName(String name) {
        for (Player tempPlayer : players) {
            if (tempPlayer.getName().equals(name)) {
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
        waves = new ArrayList();
        towers = new ArrayList();
        EntityFactory.init();

        currentWaveId = 0;
        currentWaveId = nextWave().getNumber();
    }

    public Player addPlayer(String name) {
        Player temp = getPlayerByName(name);

        if (temp == null) {
            temp = new Player(players.size(), name, this);
            players.add(temp);
        }

        return temp;
    }

    /**
     * Get the current wave that is being created by the player
     *
     * @return The current wave
     */
    public Wave getCurrentWave() {
        return waves.get(waves.size() - 1);
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

    public void startPlayingWaveState() {
        this.state = GameState.WavePlaying;
    }

    /**
     * Ends a game
     *
     * @param winner is this the winning player?
     */
    public void endGame(boolean winner) {
        this.state = GameState.Ended;
        CastleOffense.getInstance().setScreen(new EndGameMenu(winner, this));
        initializeGame(); // resets the values for the next game.
    }

    /**
     * Gets the next wave and updates the id of the wave
     *
     * @return
     */
    public Wave nextWave() {
        Wave wave = new Wave(++currentWaveId, this);
        waves.add(wave);
        this.state = GameState.InGame;
        for (Player player : players) {
            player.clearOffensives();
        }
        StatusUpdate.log("Richt de volgende ronde in!");
        GameMenu.setEnabled(true);
        return wave;
    }

    /**
     * Add a tower to the game
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
        for (Player player : players) {
            if (player.getCastle().getHitpoints() > 0) {
                boolean didDisplay = false;

                Wave lastWave = waves.get(waves.size() - 1);
                lastWave.update();
                if (lastWave.didDisplayWave()) {
                    didDisplay = true;
                }

                if (didDisplay && waves.size() > 0) {
                    nextWave();
                }
            } else {
                // this player has lost the game
                Globals.client.send(new WinGamePacket(player.getId()));
                this.state = GameState.Ended;
            }
        }
    }

    /**
     * Draws the game on the screen using the Global spritebatch
     */
    public void draw() {
        map.draw();
        for (int i = 0; i < waves.size(); i++) {
            Wave w = waves.get(i);
            w.draw();
        }
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

    public GameState getState() {
        return this.state;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof CoGame)) {
            return false;
        }
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.currentWaveId;
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + Objects.hashCode(this.map);
        hash = 79 * hash + Objects.hashCode(this.waves);
        hash = 79 * hash + Objects.hashCode(this.towers);
        hash = 79 * hash + Objects.hashCode(this.players);
        return hash;
    }
}
