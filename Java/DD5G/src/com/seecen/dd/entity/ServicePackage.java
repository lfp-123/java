package com.seecen.dd.entity;

import java.io.Serializable;

/**
 * @author ${林锋鹏}
 * @Title: ServicePackage
 * @ProjectName Java
 * @date 2019/8/15 17:05
 */
public  abstract class ServicePackage implements Serializable {


    private double price;
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public abstract void showInfo();
}
