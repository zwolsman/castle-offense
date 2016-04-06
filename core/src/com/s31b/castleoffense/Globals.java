/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s31b.castleoffense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author fhict
 */
public class Globals {

    public static final int TILES_X = 17, TILES_Y = 12;
    public static final int TILE_WIDTH = 40, TILE_HEIGHT = 40;

    //Base Prices
    public static final float PRICE_OFFENSIVE = 5;
    public static final float PRICE_DEFENSIVE = 200;

    // Base DPS
    public static final int DAMAGE_PER_SECOND = 10;
    // Base Range
    public static int DEFENSIVE_RANGE = 5;
    // Base Offensive Hitpoints
    public static int OFFENSIVE_HITPOINTS = 25;
    // Base Movement Speed
    public static int MOVEMENT_SPEED = 100;
    // Base Offensive Killreward
    public static int KILL_REWARD = 5;

    // Castle properties
    public static int CASTLE_HP = 10;
    public static Texture CASTLE_SPRITE; // TODO: add castle sprite

    //Sprite Batch
    public static SpriteBatch SPRITE_BATCH = new SpriteBatch();
}
