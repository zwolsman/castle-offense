package com.s31b.castleoffense.server.packets;

import java.util.ArrayList;

/**
 *
 * @author Marvin Zwolsman
 */
public class PlayerListPacket implements IPacket {

    public ArrayList<String> players = new ArrayList<>();

    public PlayerListPacket() {
    }

    public PlayerListPacket(String... players) {
        for (String s : players) {
            this.players.add(s);
        }
    }
}
