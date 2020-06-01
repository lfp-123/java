package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public abstract class Car {
    String type;
    int value;
    int day;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public void  toString(String type,int value,int day) {
        System.out.println("你选择的车为："+type);
        System.out.println("你选择的车价格为："+value);
        System.out.println("你选择的天数为："+day);
    }

    public double add(int day, int value){

        return  day*value;
    }
}
