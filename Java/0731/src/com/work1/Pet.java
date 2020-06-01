package com.work1;

public abstract class Pet {
    private String name;
    private int health;
    private int lover;

    public abstract void introduce();

    public String getName() {
        return this.name;
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

    public int getLover() {
        return lover;
    }

    public void setLover(int lover) {
        this.lover = lover;
    }
}
