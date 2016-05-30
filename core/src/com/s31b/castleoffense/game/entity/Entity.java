package com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.player.Player;
import java.util.Objects;

/**
 * A class that is used for everything that will be drawned in the game. Eg
 * towers, attacking npc's
 *
 * @author Goos
 */
abstract public class Entity implements Priceable {

    private static int nextId = 0;

    protected final EntityType type;

    protected final int id;
    protected final String name;
    protected final String description;
    protected final String sprite;
    private boolean first = true;
    protected final int price;

    protected Player owner;

    public Entity(EntityType type, String name, String descr, String sprite, int price, Player owner) {
        nextId++;
        this.id = nextId;
        this.type = type;
        this.name = name;
        this.description = descr;
        this.sprite = sprite;
        this.price = price;
        this.owner = owner;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSprite() {
        return sprite;
    }

    public Player getOwner() {
        return owner;
    }

    public EntityType getType() {
        return type;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Entity)) {
            return false;
        }
        Entity d = (Entity) other;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.sprite);
        hash = 47 * hash + this.price;
        hash = 47 * hash + Objects.hashCode(this.owner);
        return hash;
    }
}
