package com.seencen.morning.ExtendsTest;

/**
 * @author asus
 * @Title: Calc
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 10:55
 */
public class Calc {
    public static  double calaTotalorice(int day,MotoVehicle... motoVehicles){
        double sum= 0;
        for (MotoVehicle moto:motoVehicles) {
            double money = moto.calcPrice(day);
            sum = money+sum;
        }
        return  sum;
    }
}
