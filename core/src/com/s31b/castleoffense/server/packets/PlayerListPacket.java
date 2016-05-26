/*
 */
package com.s31b.castleoffense.server.packets;

import java.util.ArrayList;

/**
 *
 * @author fhict
 */
public class PlayerListPacket {

    public ArrayList<String> players = new ArrayList<String>();

    public PlayerListPacket() {
    }

    public PlayerListPacket(String... players) {
        for (String s : players) {
            this.players.add(s);
        }
    }
}
