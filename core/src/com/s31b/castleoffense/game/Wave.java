package com.s31b.castleoffense.game;

import com.badlogic.gdx.Gdx;
import com.s31b.castleoffense.Globals;
import com.s31b.castleoffense.TextureGlobals;
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
    private List<Offensive> killedEntities;

    private final CoGame game;

    private float timeSinceLastSpawn;
    private float spawnTime;

    public Wave(int number, CoGame game) {
        this.number = number;
        this.game = game;
        initWave();
    }

    private void initWave() {
        killedEntities = new ArrayList();
        offEntities = new ArrayList();
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
     * Gets the list of offensive entity's to the wave that will be attacking
     * the other players castle
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

    /**
     * Spawn the wave with spawn delay for each offensive entity
     */
    private void spawnWave() {
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

        StatusUpdate.log("displaying wave!");
        spawnWave();

        for (int i = 0; i < offEntities.size(); i++) {
            Offensive x = offEntities.get(i);
            if (x.isSpawned()) {
                game.getPlayers().stream().filter(player -> x.getOwner() != player).forEach(player -> {
                    checkInRange(x);
                    switch (x.update()) {
                        case "Update":
                            break;
                        case "Null":
                            player.hitCastle();
                            clearTarget(x);
                            break;
                        case "Dead":
                            reward(x);
                            clearTarget(x);
                            break;
                    }
                });
            }
        }
    }

    public void draw() {
        if (!waveDone) {
            return;
        }

        for (int i = 0; i < killedEntities.size(); i++) {
            Offensive x = killedEntities.get(i);
            x.draw(TextureGlobals.SPRITE_BATCH);
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

    private void clearTarget(Offensive o) {
        List<Defensive> defensives = game.getAllTowers();
        for (int i = 0; i < defensives.size(); i++) {
            Defensive d = defensives.get(i);
            if (d.targetAquired() && d.getTarget() == o) {
                d.deleteTarget();
            }
        }
        offEntities.remove(o);
        if (o.update() == "Dead") {
            o.die();
        }
        killedEntities.add(o);
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
        for (Player player : game.getPlayers()) {
            if (o.getOwner() != player) {
                player.addGold(o.getKillReward());
            }
        }
    }
}
