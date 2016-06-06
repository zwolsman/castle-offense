package com.s31b.castleoffense.server.packets;

/**
 *
 * @author GoosLaptop
 */
public class WinGamePacket implements IPacket {
    public int winnerid;
    
    public WinGamePacket(int winnerid) {
        this.winnerid = winnerid;
    }
}