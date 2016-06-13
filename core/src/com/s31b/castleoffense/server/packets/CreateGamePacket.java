package com.s31b.castleoffense.server.packets;

/**
 *
 * @author Marvin Zwolsman
 */
public class CreateGamePacket implements IPacket {

    public CreateGamePacket() {

    }

    public CreateGamePacket(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }
}
