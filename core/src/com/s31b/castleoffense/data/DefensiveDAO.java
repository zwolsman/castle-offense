package com.s31b.castleoffense.data;

/**
 *
 * @author Shaikun
 */
public class DefensiveDAO extends EntityDAO {

    private int DPS;
    private int Range;

    public DefensiveDAO(String type, String name, String descr, String sprite, int price, int dps, int range) {
        super(type, name, descr, sprite, price);
        this.DPS = dps;
        this.Range = range;
    }

    /**
     * @return the DPS
     */
    public int getDPS() {
        return DPS;
    }

    /**
     * @param DPS the DPS to set
     */
    public void setDPS(int DPS) {
        this.DPS = DPS;
    }

    /**
     * @return the Range
     */
    public int getRange() {
        return Range;
    }

    /**
     * @param Range the Range to set
     */
    public void setRange(int Range) {
        this.Range = Range;
    }
}
