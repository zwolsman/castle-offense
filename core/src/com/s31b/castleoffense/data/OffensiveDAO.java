package com.s31b.castleoffense.data;

/**
 *
 * @author Shaikun
 */
public class OffensiveDAO extends EntityDAO {

    private int healthPoints;
    private int speed;
    private int reward;

    public OffensiveDAO(String type, String name, String descr, String sprite, int price, int hp, int speed, int reward) {
        super(type, name, descr, sprite, price);
        this.healthPoints = hp;
        this.speed = speed;
        this.reward = reward;
    }

    /**
     * @return the healthPoints
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * @param healthPoints the healthPoints to set
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param Speed the speed to set
     */
    public void setSpeed(int Speed) {
        this.speed = Speed;
    }

    /**
     * @return the reward
     */
    public int getReward() {
        return reward;
    }

    /**
     * @param Reward the reward to set
     */
    public void setReward(int Reward) {
        this.reward = Reward;
    }
}
