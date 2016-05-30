/*
 */
package com.s31b.castleoffense.server.packets;

/**
 *
 * @author fhict
 */
public class JoinGamePacket implements IPacket {

    public JoinGamePacket() {
    }

    public JoinGamePacket(int gameId) {
        gid = gameId;
    }
    public int gid;
}
