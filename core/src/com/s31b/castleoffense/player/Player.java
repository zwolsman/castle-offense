package com.s31b.castleoffense.player;

import com.badlogic.gdx.math.Vector2;
import com.s31b.castleoffense.EntityFactory;
import com.s31b.castleoffense.game.CoGame;
import com.s31b.castleoffense.game.entity.*;
import com.s31b.castleoffense.map.Tile;

/**
 *
 * @author Goos
 */
public class Player {

    private final int id;
    private String name;
    private int points;
    private int gold;
    private Tile offensiveSpawnPosition;

    private Castle castle;

    private CoGame game;

    public Player(int id, String name, CoGame game) {
        this.id = id;
        this.name = name;
        this.game = game;
        initPlayer();
    }

    private void initPlayer() {
        points = 0;
        gold = 100;
        offensiveSpawnPosition = new Tile(0, 1); // change to castle door tile
        castle = new Castle(this);
    }

    /**
     * Removes currency from buying player
     *
     * @return bought offensive entity, or null if not enough money
     */
    public boolean BuyOffensiveEntity(EntityType type) {
        float price = EntityFactory.getEntityPriceByType(type);
        if (price < this.gold) {
            this.gold -= price;
            Offensive entity = (Offensive) EntityFactory.buyEntity(type, this);
            if (entity == null) {
                return false;
            }

            game.getCurrentWave().addOffensive(entity);
            return true;
        }

        return false;
    }

    /**
     * Removes currency from buying player
     *
     * @return bought defensive entity, or null if not enough money
     */
    public boolean BuyDefensiveEntity(EntityType type, Tile location) {
        float price = EntityFactory.getEntityPriceByType(type);
        if (price < this.gold) {
            this.gold -= price;
            Defensive entity = (Defensive) EntityFactory.buyEntity(type, this);
            if (entity == null) {
                return false;
            }

            game.addTower(entity);
            return true;
        }

        return false;
    }

    public void addPoints(int ammount) {
        points += ammount;
    }

    public void addGold(int ammount) {
        gold += ammount;
    }

    public String getName() {
        return name;
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
}
