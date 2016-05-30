package com.s31b.castleoffense.server.packets;

/**
 *
 * @author Marvin Zwolsman
 */
public class JoinGamePacket implements IPacket {

    public JoinGamePacket() {
    }

    public JoinGamePacket(int gameId) {
        gid = gameId;
    }
    int gid;
}
