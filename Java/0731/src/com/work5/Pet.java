package com.work5;

public abstract class Pet {
    public abstract void toHospital();
    private int health;
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

}
