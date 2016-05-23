package com.s31b.castleoffense.game.entity;

/**
 * The types of entities.
 *
 * @author GoosLaptop
 */
public enum EntityType {
    //Defensive
    Defensive_Tower1(1),
    //Offensive
    Offensive_Npc1(1);

    private final int multiplyFactor;

    private EntityType(int multiplyFactor) {
        this.multiplyFactor = multiplyFactor;
    }

    public int getMultiplyFactor() {
        return multiplyFactor;
    }
}
