package com.s31b.castleoffense.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.s31b.castleoffense.game.Clock.Delta;
import com.s31b.castleoffense.map.Tile;
import com.s31b.castleoffense.player.Player;

/**
 *
 * @author Goos
 */
abstract public class Entity implements Priceable {
    private static int nextId = 0;
    
    protected final EntityType type;
    
    protected final int id;
    protected final String name;
    protected final String description;
    protected final Texture sprite;
    private boolean first = true;
    protected final float price;
    
    protected Player owner;
    
    public Entity(EntityType type, String name, String descr, Texture sprite, float price, Player owner) {
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

    public Texture getSprite() {
        return sprite;
    }

    public Player getOwner() {
        return owner;
    }
    
    @Override
    public float getPrice() {
        return this.price;
    }
}
