package com.s31b.castleoffense.server.packets;

import java.util.ArrayList;

/**
 *
 * @author Marvin Zwolsman
 */
public class GameListPacket implements IPacket {

    public GameListPacket() {

    }
    public ArrayList<String> games = new ArrayList<>();
    public ArrayList<Integer> ids = new ArrayList<>();
    public ArrayList<Integer> max = new ArrayList<>();
    public ArrayList<Integer> current = new ArrayList<>();
}
