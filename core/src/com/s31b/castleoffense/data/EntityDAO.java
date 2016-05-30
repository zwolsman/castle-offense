package com.s31b.castleoffense.data;

/**
 *
 * @author Shaikun
 */
public abstract class EntityDAO {

    private String Type;
    private String Name;
    private String Descr;
    private String Sprite;
    private int Price;

    public EntityDAO(String type, String name, String descr, String sprite, int price) {
        this.Type = type;
        this.Name = name;
        this.Descr = descr;
        this.Sprite = sprite;
        this.Price = price;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Descr
     */
    public String getDescr() {
        return Descr;
    }

    /**
     * @param Descr the Descr to set
     */
    public void setDescr(String Descr) {
        this.Descr = Descr;
    }

    /**
     * @return the Sprite
     */
    public String getSprite() {
        return Sprite;
    }

    /**
     * @param Sprite the Sprite to set
     */
    public void setSprite(String Sprite) {
        this.Sprite = Sprite;
    }

    /**
     * @return the Price
     */
    public int getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(int Price) {
        this.Price = Price;
    }
}
