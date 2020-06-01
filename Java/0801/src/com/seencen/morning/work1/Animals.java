package com.seencen.morning.work1;

/**
 * @author asus
 * @Title: Animals
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 10:00
 */
public abstract class Animals {
    abstract void eat();
    abstract void toHospital();
    private  String name;
    private   int health;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
