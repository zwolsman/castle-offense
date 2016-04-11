package com.s31b.castleoffense.game;

import com.badlogic.gdx.Gdx;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.entity.*;
import java.util.ArrayList;
import java.util.List;
import static com.s31b.castleoffense.game.Clock.*;

/**
 *
 * @author Goos
 */
public class Wave {

    private int number;

    private boolean player1done, player2done, waveDone;

    private List<Defensive> defEntities;
    private List<Offensive> offEntities;

    private final CoGame game;

    private float timeSinceLastSpawn, spawnTime;

    public Wave(int number, CoGame game) {
        this.number = number;
        this.game = game;
        initWave();
    }

    private void initWave() {
        defEntities = new ArrayList<Defensive>();
        offEntities = new ArrayList<Offensive>();
        player1done = false;
        player2done = false;
        waveDone = false;
        spawnTime = 5;
    }

    public int getNumber() {
        return this.number;
    }

    public void addDefensive(Defensive entity) {
        defEntities.add(entity);
    }

    public void addOffensive(Offensive entity) {
        offEntities.add(entity);
    }

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
            // Lambda Runnable

// start the thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    spawnWave();
                }
            }).start();
        }
    }

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
        // if (waveDone) {
        // display current wave

        for (Defensive entity : defEntities) {
            // add towers to game
            game.addTower(entity);
        }

        for (Offensive entity : offEntities) {
            // draw entities with delay
            if (entity.isSpawned()) {
                entity.update();
            }

        }
        //}
    }

    public void draw() {
        for (Defensive entity : defEntities) {
            // add towers to game

        }

        for (Offensive entity : offEntities) {
            entity.draw(Globals.SPRITE_BATCH);
        }
    }
}
