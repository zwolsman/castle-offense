package com.s31b.castleoffense.game.entity;

/**
 * The types of entities.
 *
 * @author GoosLaptop
 */
public enum EntityType {
    //Defensive
    Defensive_Tower1(1.0f),
    //Offensive
    Offensive_Npc1(1.0f);

    private final float multiplyFactor;

    private EntityType(float multiplyFactor) {
        this.multiplyFactor = multiplyFactor;
    }

    public float getMultiplyFactor() {
        return multiplyFactor;
    }
}
