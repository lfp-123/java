package com.homeworks.homework2;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class Fish extends Animals {

       public  Fish(double height){
            setHeight(height);
        }
    double height;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    void introduce() {
        System.out.println("我的鱼，我的重量是："+getHeight()+"我的年龄是："+getAge());
    }
}
