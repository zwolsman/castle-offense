package com.s31b.castleoffense;

import com.s31b.castleoffense.server.KryoClient;

/**
 *
 * @author Marvin Zwolsman
 */
public class Globals {

    public static final Boolean DEBUG = false;
    public static final int TILES_X = 17 * 2, TILES_Y = 12;
    public static final int TILE_WIDTH = 40, TILE_HEIGHT = 40;
    public static KryoClient client;
    //Base Prices
    public static final int PRICE_OFFENSIVE = 5;
    public static final int PRICE_DEFENSIVE = 200;
    public static final int GOLD_INCR_PER_WAVE = 200;

    // Base DPS
    public static final int DAMAGE_PER_SECOND = 10;
    // Base Range
    public static final int DEFENSIVE_RANGE = 5;
    // Base Offensive Hitpoints
    public static final int OFFENSIVE_HITPOINTS = 25;
    // Base Movement Speed
    public static final int MOVEMENT_SPEED = 100;
    // Base Offensive Killreward
    public static final int KILL_REWARD = 5;

    // Castle properties
    public static final int CASTLE_HP = 10;

}
