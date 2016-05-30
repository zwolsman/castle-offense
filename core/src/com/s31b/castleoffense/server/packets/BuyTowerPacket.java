/*
 */
package com.s31b.castleoffense.server.packets;

/**
 *
 * @author fhict
 */
public class BuyTowerPacket implements IPacket {

    public BuyTowerPacket() {
    }

    public BuyTowerPacket(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public int x, y;
    public String name;

}
