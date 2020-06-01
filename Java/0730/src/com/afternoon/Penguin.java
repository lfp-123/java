package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class Penguin extends Pet {

    private  String gender;

    public Penguin(String name, int health, int lover, String gender) {
        setName(name);
        setHealth(health);
        setLover(lover);
        setGender(gender);
    }
    public String getGender() {
        return gender;
    }
    @Override
    public String toString() {


        return "Pet{" +
                "name='" + this.getName() + '\'' +
                ", health=" + this.getHealth() +
                ", lover=" +this. getLover()+ this.getGender()+
                '}';
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
