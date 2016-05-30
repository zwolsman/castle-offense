package com.s31b.castleoffense.server.packets;

/**
 *
 * @author Marvin Zwolsman
 */
public class BoughtTowerPacket implements IPacket {

    public int x;
    public int y;
    public int pid;
    public String name;

    public BoughtTowerPacket() {
    }

    public BoughtTowerPacket(int x, int y, int pid, String name) {
        this.x = x;
        this.y = y;
        this.pid = pid;
        this.name = name;
    }
}
