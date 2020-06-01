package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class Dog extends Pet{

  private String type;

    public Dog(String name, int health, int lover, String gender) {
        setName(name);
        setHealth(health);
        setLover(lover);
        setType(gender);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {


        return "Pet{" +
                "name='" + this.getName() + '\'' +
                ", health=" + this.getHealth() +
                ", lover=" +this. getLover()+ this.getType()+
                '}';
    }
}
