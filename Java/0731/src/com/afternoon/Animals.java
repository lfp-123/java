package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public abstract  class Animals {
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private int health;

    public  abstract void toHospital();
}
