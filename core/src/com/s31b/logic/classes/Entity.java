package com.s31b.logic.classes;

import com.s31b.logic.interfaces.Pricable;
import java.awt.image.BufferedImage;

/**
 *
 * @author Goos
 */
abstract public class Entity implements Pricable {
    private static int nextId = 0;
    
    private final int id;
    private final String name;
    private final String description;
    private final BufferedImage sprite;
    
    private final float price;
    
    private Player owner;
    
    public Entity(String name, String descr, BufferedImage sprite, float price, Player owner) {
        nextId++;
        this.id = nextId;
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
    public boolean Buy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrice(float price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
