package com.sc.homework.简答题;

public abstract class  Animal {
    private  int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal(int age) {

        this.age = age;
    }

    public abstract void  info();
}
