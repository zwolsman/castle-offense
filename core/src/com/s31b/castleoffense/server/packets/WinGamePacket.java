package com.s31b.castleoffense.server.packets;

/**
 *
 * @author GoosLaptop
 */
public class WinGamePacket implements IPacket {

    public int loserid;

    public WinGamePacket() {
    }

    public WinGamePacket(int loserid) {
        this.loserid = loserid;
    }
}
