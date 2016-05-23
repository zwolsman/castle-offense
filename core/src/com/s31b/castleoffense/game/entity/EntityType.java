package com.s31b.castleoffense.game.entity;

/**
 * The types of entities.
 *
 * @author GoosLaptop
 */
public enum EntityType {
    //Defensive
    Tower_Green(200),
    Tower_Pink(400),
    Tower_Blue(800),
    Tower_Yellow(1600),
    //Offensive
    Brown(5),
    Blue(10),
    Green(20),
    Black(40),
    Police(80),
    Military(200);

    private final int multiplyFactor;

    private EntityType(int multiplyFactor) {
        this.multiplyFactor = multiplyFactor;
    }

    public int getMultiplyFactor() {
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
