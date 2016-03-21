package com.s31b.castleoffense.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.s31b.castleoffense.game.entity.Defensive;
import com.s31b.castleoffense.map.Map;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Goos
 * CoGame is short for Castle Offense Game
 * this is because "Game" is a default libGDX class
 */
public class CoGame {
    private int id;
    
    private int currentWaveId = 0;
    
    private GameState state;
    
    private Map map;
    private List<Wave> waves;
    private List<Defensive> towers;
    
    private Player player1;
    private Player player2;
    
    public CoGame (int id) {
        this.id = id;
        initializeGame();
    }
    
    public void initializeGame() {
        state = GameState.StartMenu;
        map = new Map();
        waves = new ArrayList<Wave>();
        player1 = new Player(1, "Speler 1", this);
        player2 = new Player(2, "Speler 2", this);
    }
    
    public Wave getCurrentWave() {
        for (Wave wave : waves) {
            if (wave.getNumber() == currentWaveId) {
                return wave;
            }
        }
        return null;
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

//    public void addMap(Map map) {
//        maps.add(map);
//    }

    public Wave nextWave() {
        currentWaveId++;
        Wave wave = new Wave(currentWaveId);
        waves.add(wave);
        return wave;
    }
    
    public void addTower(Defensive tower) {
        towers.add(tower);
    }
    
    public List<Defensive> getAllTowers() {
        return Collections.unmodifiableList(towers);
    }
    
    /**
     * Ends the given wave
     * @param wave wave to be ended
     */
    public void endWave (Wave wave) {
        // TODO: logic to end wave
        throw new UnsupportedOperationException();
    }
    
    public void draw(SpriteBatch batch) {
        map.draw(batch);
    }
}
