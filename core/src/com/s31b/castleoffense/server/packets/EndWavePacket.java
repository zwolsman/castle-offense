package com.s31b.castleoffense.server.packets;

import java.util.List;

/**
 *
 * @author Marvin Zwolsman
 */
public class EndWavePacket implements IPacket {

    public List<Integer> entities;
    public int pid;

}
