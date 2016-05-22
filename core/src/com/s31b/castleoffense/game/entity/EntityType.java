package com.s31b.castleoffense.game.entity;

/**
 * The types of entities.
 *
 * @author GoosLaptop
 */
public enum EntityType {
    //Defensive
    Tower_Green(200.0f),
    Tower_Pink(400.0f),
    Tower_Blue(800.0f),
    Tower_Yellow(1600.0f),
    //Offensive
    Brown(5.0f),
    Blue(10.0f),
    Green(20.0f),
    Black(40.0f),
    Police(80.0f),
    Military(200.0f);

    private final float multiplyFactor;

    private EntityType(float multiplyFactor) {
        this.multiplyFactor = multiplyFactor;
    }

    public float getMultiplyFactor() {
        return multiplyFactor;
    }

    public static EntityType getTypeFromString(String s) {
        if (s != null) {
            for (EntityType e : EntityType.values()) {
                if (s.equalsIgnoreCase(e.name())) {
                    return e;
                }
            }
        }
        return null;
    }
}
