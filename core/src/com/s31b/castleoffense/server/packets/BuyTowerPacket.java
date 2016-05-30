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
    int x;
    int y;
    String name;

}
