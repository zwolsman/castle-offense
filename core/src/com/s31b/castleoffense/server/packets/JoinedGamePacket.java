/*
 */
package com.s31b.castleoffense.server.packets;

/**
 *
 * @author fhict
 */
public class JoinedGamePacket implements IPacket {

    public JoinedGamePacket() {
    }

    public JoinedGamePacket(String name) {
        this.name = name;
    }
    public String name;
}
