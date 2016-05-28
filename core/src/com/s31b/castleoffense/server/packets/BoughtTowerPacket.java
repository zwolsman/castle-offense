/*
 */
package com.s31b.castleoffense.server.packets;

/**
 *
 * @author fhict
 */
public class BoughtTowerPacket implements IPacket {

    public int x, y, pid;
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
