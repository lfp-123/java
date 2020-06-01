package com.sc.homework.简答题;

public class Bird extends Animal {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bird(String color,int age) {
        super(age);
        this.color = color;
    }

    @Override
    public void info() {
        System.out.println("我是一只"+this.getColor()+"的鸟");
        System.out.println("我今年"+this.getAge()+"岁拉！");
    }
}
