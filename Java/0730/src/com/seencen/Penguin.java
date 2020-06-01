package com.seencen;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class Penguin {
    private String name;
    private String sex;
    private int health;

    public static final String GENDER_MALE="迪丽热巴";
    public static final String GENDER_FEMALE="女";

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

    public Penguin(String name, String sex, int health) {
        this.name = name;
        this.sex = sex;
        this.health = health;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", health=" + health +
                '}';
    }
}
