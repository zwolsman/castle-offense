package com.s31b.castleoffense.game.entity;

import com.s31b.castleoffense.player.Player;
import java.awt.image.BufferedImage;

/**
 *
 * @author Goos
 */
abstract public class Entity implements Priceable {
    private static int nextId = 0;
    
    private final EntityType type;
    
    private final int id;
    private final String name;
    private final String description;
    private final BufferedImage sprite;
    
    private final float price;
    
    private Player owner;
    
    public Entity(EntityType type, String name, String descr, BufferedImage sprite, float price, Player owner) {
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

    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public float getPrice() {
        return this.price;
    }
}
