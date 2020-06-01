package com.sc.homework.简答题;

public class Fish extends Animal {

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Fish(int age, int weight) {

        super(age);
        this.weight = weight;
    }

    @Override
    public void info() {
        System.out.println("我是一只"+this.getWeight()+"斤重的鱼");
        System.out.println("我今年"+this.getAge()+"岁拉！");
    }
}
