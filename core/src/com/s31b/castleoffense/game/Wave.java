package com.s31b.castleoffense.game;

import com.badlogic.gdx.Gdx;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureGlobals;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Goos
 */
public class Wave {

    private final int number;
    private Object obj = new Object();
    private boolean waveDone;
    
    private int playersDone;

    private List<Offensive> offEntities;
    private List<Offensive> killedEntities;

    private final CoGame game;

    private float timeSinceLastSpawn, spawnTime;

    public Wave(int number, CoGame game) {
        this.number = number;
        this.game = game;
        initWave();
    }

    private void initWave() {
        offEntities = new ArrayList();
        killedEntities = new ArrayList();
        waveDone = false;
        spawnTime = 5;
        playersDone = 0;
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * Adds an offensive entity to the wave that will be attacking the other
     * players castle
     *
     * @param entity A class that is derived from the Offensive class
     */
    public void addOffensive(Offensive entity) {
        offEntities.add(entity);
    }
    
    /**
     * Ends the wave. If all players ended the wave the wave will be played.
     * End wave means end of turn
     *
     */
    public void endWave() {
        playersDone++;

        if (playersDone >= game.getPlayers().size()) {
            waveDone = true;
            for (Player player : game.getPlayers()) {
                player.addGold(Globals.GOLD_INCR_PER_WAVE);
            }
        }
    }

    /**
     * Spawn the wave with spawn delay for each offensive entity
     */
    private void spawnWave() {
        // entity.update();
        synchronized (obj) {
            for (Offensive entity : offEntities) {
                timeSinceLastSpawn += Gdx.graphics.getDeltaTime();

                if (timeSinceLastSpawn > spawnTime && !entity.isSpawned()) {
                    entity.spawn();
                    timeSinceLastSpawn = 0;
                }
            }
        }
    }

    public void update() {
        if (!waveDone) {
            return;
        }

        spawnWave();

        for (int i = 0; i < offEntities.size(); i++) {
            Offensive x = offEntities.get(i);
            if (x.isSpawned()) {
                for (Player player : game.getPlayers()) {
                    if (x.getOwner() != player) {
                        checkInRange(x);
                        if (x.isDead()) {
                            killedEntities.add(x);
                            reward(x);
                            clearTarget(x);
                            offEntities.remove(x);
                        } else if (!x.update()) {

                            player.hitCastle();
                            System.out.println("Hit:" + player.getCastle().getHitpoints());
                            clearTarget(x);
                            offEntities.remove(x);
                        }
                    }
                }
            }
        }
    }

    public void draw() {
        if (!waveDone) {
            return;
        }

        for (int i = 0; i < offEntities.size(); i++) {
            Offensive x = offEntities.get(i);
            x.draw(TextureGlobals.SPRITE_BATCH);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Wave)) {
            return false;
        }
        Wave w = (Wave) other;
        return this.playersDone == w.playersDone
                && this.waveDone == w.waveDone
                && this.game.equals(w.game)
                && this.number == w.number
                && this.spawnTime == w.spawnTime
                && this.timeSinceLastSpawn == w.timeSinceLastSpawn;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.number;
        hash = 41 * hash + (this.waveDone ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.game);
        hash = 41 * hash + Float.floatToIntBits(this.timeSinceLastSpawn);
        hash = 41 * hash + Float.floatToIntBits(this.spawnTime);
        return hash;
    }

    private void clearTarget(Offensive o) {
        List<Defensive> defensives = game.getAllTowers();
        for (int i = 0; i < defensives.size(); i++) {
            Defensive d = defensives.get(i);
            if (d.targetAquired() && d.getTarget() == o) {
                d.deleteTarget();
            }
        }
    }

    private void checkInRange(Offensive o) {
        List<Defensive> defensives = game.getAllTowers();
        for (int i = 0; i < defensives.size(); i++) {
            Defensive d = defensives.get(i);
            /*if (d.targetAquired() && d.getTarget() == o && d.inRange(o)) {
                o = d.dealDamage();
            } else if (d.inRange(o) && !d.targetAquired()) {
                d.setTarget(o);
                o = d.dealDamage();
            }*/

            if (d.inRange(o)) {
                if (!d.targetAquired() || !d.inRange(d.getTarget())) {
                    d.setTarget(o);
                }
                o = d.dealDamage();
                System.out.println(o.getHitpoints());
            }
        }
    }

    public void reward(Offensive o) {
        for (Player player : game.getPlayers()) {
            if (o.getOwner() != player) {
                player.addGold(o.getKillReward());
            }
        }
    }
}
