package com.s31b.castleoffense.server.packets;

/**
 *
 * @author Marvin Zwolsman
 */
public class BuyTowerPacket implements IPacket {

    public BuyTowerPacket() {
    }

    public BuyTowerPacket(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    private int x;
    private int y;
    private String name;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
