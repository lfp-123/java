package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class MotoCar extends Car {


    private  int price ;
        MotoCar(int price){
            int prices =0;
            setPrice(price);

        }
    public double add(int day){
            return  this.price*day;
        }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
