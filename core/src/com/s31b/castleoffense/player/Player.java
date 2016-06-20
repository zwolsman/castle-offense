package com.s31b.castleoffense.player;

import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A player instance
 *
 * @author Goos
 */
public class Player {

    private final int id;
    private String name;
    private int points;
    private int gold;
    private Tile offensiveSpawnPosition;
    private List<Offensive> offensives;
    private Castle castle;

    private final CoGame game;

    public Player(int id, String name, CoGame game) {
        this.id = id;
        this.name = name;
        this.game = game;
        initPlayer();
    }

    private void initPlayer() {
        points = 0;
        gold = 300;
        offensiveSpawnPosition = game.getMap().getSpawnPoints().get(id);
        castle = new Castle(this);
        offensives = new ArrayList<Offensive>();
    }

    /**
     * Removes currency from buying player
     *
     * @return bought offensive entity, or null if not enough money
     */
    public Offensive buyOffensiveEntity(EntityType type) {
        Offensive entity = (Offensive) EntityFactory.buyEntity(type, this);
        float price = entity.getPrice();
        if (price <= this.gold) {
            this.gold -= price;
            offensives.add(entity);
            return entity;
        }
        return null;
    }

    /**
     * Removes currency from buying player
     *
     * @return bought defensive entity, or null if not enough money
     */
    public Defensive buyDefensiveEntity(EntityType type) {
        Defensive entity = (Defensive) EntityFactory.buyEntity(type, this);
        float price = entity.getPrice();
        if (price <= this.gold) {
            this.gold -= price;

            game.addTower(entity);
            return entity;
        }
        return null;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public String getName() {
        return name;
    }

    public void hitCastle() {
        castle.loseHitpoints(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Castle getCastle() {
        return this.castle;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public int getGold() {
        return gold;
    }

    public Tile getOffensiveSpawnPosition() {
        return offensiveSpawnPosition;
    }

    public CoGame getGame() {
        return this.game;
    }

    public List<Offensive> getOffensives() {
        return Collections.unmodifiableList(offensives);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Player)) {
            return false;
        }
        Player p = (Player) other;
        return this.game == p.game
                && this.castle.equals(p.castle)
                && this.gold == p.gold
                && this.id == p.id
                && this.name == p.name
                && this.offensiveSpawnPosition.equals(p.offensiveSpawnPosition)
                && this.points == p.points;
    }
}
