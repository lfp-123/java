package com.work4;

public abstract class MotoVehicle {
    // 车牌号
    private String no;
    // 颜色
    private String color;
    // 里程数
    private int mileage;

    // 抽象的租赁方法
    public abstract double calcPrice(int days);

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
