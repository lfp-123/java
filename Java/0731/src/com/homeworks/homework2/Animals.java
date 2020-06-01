package com.homeworks.homework2;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public abstract class Animals {
    private int age = 20;





    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


     abstract void introduce();
}
