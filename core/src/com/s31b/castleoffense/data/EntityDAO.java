/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense.data;

/**
 *
 * @author Shaikun
 */
public abstract class EntityDAO {

    private String type;
    private String name;
    private String descr;
    private String sprite;
    private int price;

    public EntityDAO(String type, String name, String descr, String sprite, int price) {
        this.type = type;
        this.name = name;
        this.descr = descr;
        this.sprite = sprite;
        this.price = price;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param Type the type to set
     */
    public void setType(String Type) {
        this.type = Type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param Name the name to set
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param Descr the descr to set
     */
    public void setDescr(String Descr) {
        this.descr = Descr;
    }

    /**
     * @return the sprite
     */
    public String getSprite() {
        return sprite;
    }

    /**
     * @param Sprite the sprite to set
     */
    public void setSprite(String Sprite) {
        this.sprite = Sprite;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param Price the price to set
     */
    public void setPrice(int Price) {
        this.price = Price;
    }
}
