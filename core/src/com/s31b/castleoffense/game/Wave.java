package com.s31b.castleoffense.game;

import com.badlogic.gdx.Gdx;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.player.Player;
import com.s31b.castleoffense.ui.StatusUpdate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goos
 */
public class Wave {

    private final int number;
    private boolean waveDone;

    private int playersDone;

    private List<Offensive> offEntities;

    private final CoGame game;

    private float timeSinceLastSpawn;
    private float spawnTime;

    public Wave(int number, CoGame game) {
        this.number = number;
        this.game = game;
        initWave();
    }

    private void initWave() {
        offEntities = new ArrayList();
        waveDone = false;
        spawnTime = 2;
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
     * Gets the list of offensive entity's to the wave that will be attacking
     * the other players castle
     *
     * @return returns the list of offensive entities
     */
    public List<Offensive> getOffensives() {
        return this.offEntities;
    }

    /**
     * Ends the wave. If all players ended the wave the wave will be played. End
     * wave means end of turn
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

    public boolean didSpawnWave() {

        if (offEntities.isEmpty()) {
            return false;
        }
        for (Offensive entity : offEntities) {
            if (!entity.isSpawned()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Spawn the wave with spawn delay for each offensive entity
     */
    private void spawnWave() {

        for (Offensive entity : offEntities) {

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
        timeSinceLastSpawn += Gdx.graphics.getDeltaTime();
        StatusUpdate.log("displaying wave!");
        spawnWave();

        for (int i = 0; i < offEntities.size(); i++) {
            Offensive offensive = offEntities.get(i);

            if (offensive.isSpawned()) {
                offensive.update();
                game.getPlayers().stream().filter(player -> offensive.getOwner() != player).forEach(player -> {
                    checkInRange(offensive);
                    if (offensive.isDead()) {
                        reward(offensive);
                    }
                });
            }
        }
    }

    public void draw() {
        if (!waveDone) {
            return;
        }

        for (int i = 0; i < offEntities.size(); i++) {
            Offensive x = offEntities.get(i);
            x.draw();
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
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.number;
        hash = 41 * hash + (this.waveDone ? 1 : 0);
        hash = 41 * hash + Float.floatToIntBits(this.timeSinceLastSpawn);
        hash = 41 * hash + Float.floatToIntBits(this.spawnTime);
        return hash;
    }

    private void checkInRange(Offensive offensive) {
        List<Defensive> defensives = game.getAllTowers();
        for (int i = 0; i < defensives.size(); i++) {
            Defensive tower = defensives.get(i);

            if (tower.inRange(offensive) && tower.getOwner() != offensive.getOwner()) {
                if (!tower.targetAquired() || !tower.inRange(tower.getTarget())) {
                    tower.setTarget(offensive);
                }
                tower.dealDamage();
            }
        }
    }

    public void reward(Offensive o) {
        o.getDamageSource().getOwner().addGold(o.getKillReward());
    }
}
