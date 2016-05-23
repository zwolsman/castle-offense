package com.s31b.castleoffense.game;

import com.badlogic.gdx.Gdx;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goos
 */
public class Wave {

    private int number;

    private boolean player1done, player2done, waveDone;

    private List<Offensive> offEntities;

    private final CoGame game;

    private float timeSinceLastSpawn, spawnTime;

    public Wave(int number, CoGame game) {
        this.number = number;
        this.game = game;
        initWave();
    }

    private void initWave() {
        offEntities = new ArrayList<Offensive>();
        player1done = false;
        player2done = false;
        waveDone = false;
        spawnTime = 5;
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * Adds an offensive entity to the wave that will be attacking the other
     * players castle
     *
     * @param entity A class that is derrived from the Offensive class
     */
    public void addOffensive(Offensive entity) {
        offEntities.add(entity);
    }

    /**
     * Ends the wave. If both players ended the wave the wave will be played.
     * End wave means end of turn
     *
     * @param playerId
     */
    public void endWave(int playerId) {
        switch (playerId) {
            case 1:
                player1done = true;
                break;
            case 2:
                player2done = true;
                break;
            default:
                break;
        }

        if (player1done && player2done) {
            waveDone = true;
            for(Player player : game.getPlayers()){
                player.addGold(Globals.GOLD_INCR_PER_WAVE);
            }
        }
    }

    /**
     * Spawn the wave with spawn delay for each offensive entity
     */
    private void spawnWave() {
        // entity.update();
        for (Offensive entity : offEntities) {
            timeSinceLastSpawn += Gdx.graphics.getDeltaTime();

            if (timeSinceLastSpawn > spawnTime && !entity.isSpawned()) {
                entity.spawn();
                timeSinceLastSpawn = 0;
            }
        }
    }

    public void update() {
        if (!waveDone) {
            return;
        }

        spawnWave();

        for (Offensive entity : offEntities) {
            if (entity.isSpawned()) {
                entity.update();
            }
        }
    }

    public void draw() {
        if (!waveDone) {
            return;
        }

        for (Offensive entity : offEntities) {
            entity.draw(TextureGlobals.SPRITE_BATCH);
        }
    }
}
