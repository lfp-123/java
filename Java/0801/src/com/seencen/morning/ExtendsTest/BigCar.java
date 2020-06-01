package com.seencen.morning.ExtendsTest;

/**
 * @author asus
 * @Title: BigCar
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 11:14
 */
public class BigCar extends MotoVehicle {
    private int ton ;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price =50;

    BigCar(int ton){
        setTon(ton);
    }



    public int getTon() {
        return ton;
    }

    public void setTon(int ton) {
        this.ton = ton;
    }

    @Override
    public double calcPrice(int days) {
        double sum =0;

        sum = days* getPrice()*getTon();

        return  sum;
    }
}
