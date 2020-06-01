package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public abstract class Pet {
    @Override
    public abstract String toString() ;


    private String name;
    private  int health;
    private int lover;

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
        if(health>100||health<0){
            health=60;
            System.out.println("健康值应该为60-100之间!");
        }
        this.health = health;

    }

    public int getLover() {
        return lover;
    }

    public void setLover(int lover) {
        this.lover = lover;
    }


}
