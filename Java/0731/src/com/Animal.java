package com;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public abstract class Animal {
    private String  name = "狗";
    private String  sex;
    private int health;

    public  Animal(){
        System.out.println("父类对象"+name);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public  abstract void introduce( );
}
