package com.homeworks.homework2;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class Bird extends  Animals{


    String color ;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    void introduce() {
        System.out.println("我是鸟我的颜色是："+getColor()+"我的年龄是："+getAge());
    }
}
