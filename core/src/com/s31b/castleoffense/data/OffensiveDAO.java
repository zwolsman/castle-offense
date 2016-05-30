package com.s31b.castleoffense.data;

/**
 *
 * @author Shaikun
 */
public class OffensiveDAO extends EntityDAO {

    private int HP;
    private int Speed;
    private int Reward;

    public OffensiveDAO(String type, String name, String descr, String sprite, int price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price);
        this.HP = hp;
        this.Speed = speed;
        this.Reward = reward;
    }

    /**
     * @return the HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * @param HP the HP to set
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * @return the Speed
     */
    public int getSpeed() {
        return Speed;
    }

    /**
     * @param Speed the Speed to set
     */
    public void setSpeed(int Speed) {
        this.Speed = Speed;
    }

    /**
     * @return the Reward
     */
    public int getReward() {
        return Reward;
    }

    /**
     * @param Reward the Reward to set
     */
    public void setReward(int Reward) {
        this.Reward = Reward;
    }
}
