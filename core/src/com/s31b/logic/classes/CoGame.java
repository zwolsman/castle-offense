package com.s31b.logic.classes;

import com.s31b.logic.enums.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goos
 * CoGame is short for Castle Offense Game
 * this is because "Game" is a default libGDX class
 */
public class CoGame {
    private int id;
    
    private int currentWave = 0;
    
    private GameState state;
    
    private List<Map> maps;
    private List<Wave> waves;
    
    private Player player1;
    private Player player2;
    
    public CoGame (int id) {
        this.id = id;
        initializeGame();
    }
    
    public void initializeGame() {
        state = GameState.StartMenu;
        maps = new ArrayList<Map>();
        waves = new ArrayList<Wave>();
        player1 = new Player(1, "Speler 1");
        player2 = new Player(2, "Speler 2");
    }
    
    public int getId () {
        return this.id;
    }
    
    public void startGame () {
        this.state = GameState.InGame;
    }
    
    public void pauseGame () {
        this.state = GameState.Paused;
    }
    
    public void restartGame () {
        this.state = GameState.StartMenu;
    }
    
    public void endGame () {
        this.state = GameState.Ended;
    }

    public void addMap(Map map) {
        maps.add(map);
    }

    public Wave nextWave() {
        currentWave++;
        Wave wave = new Wave(currentWave);
        waves.add(wave);
        return wave;
    }
    
    /**
     * Displays a given wave to all players
     * @param wave wave to be displayed
     */
    public void displayWave (Wave wave) {
        // TODO: logic to display wave
        throw new UnsupportedOperationException();
    }
    
    /**
     * Ends the given wave
     * @param wave wave to be ended
     */
    public void endWave (Wave wave) {
        // TODO: logic to end wave
        throw new UnsupportedOperationException();
    }
}
