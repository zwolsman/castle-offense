package com.s31b.castleoffense.data;

/**
 *
 * @author Shaikun
 */
public class DefensiveDAO extends EntityDAO {

    private int damage;
    private int range;

    public DefensiveDAO(String type, String name, String descr, String sprite, int price, int dps, int range) {
        super(type, name, descr, sprite, price);
        this.damage = dps;
        this.range = range;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param DPS the damage to set
     */
    public void setDamage(int DPS) {
        this.damage = DPS;
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param Range the range to set
     */
    public void setRange(int Range) {
        this.range = Range;
    }
}
