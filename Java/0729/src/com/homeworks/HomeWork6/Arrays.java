package com.homeworks.HomeWork6;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Arrays {

    public static void main(String[] args) {
        int nums[] = {123,56,789,96,74,56,50};
        Arrayssort arrayssort = new Arrayssort();
        arrayssort.show(nums);
        int max =   arrayssort.Max(nums);
        System.out.println("最大值为："+max);
        int min = arrayssort.min(nums);
        System.out.println("最小值为："+min);


    }
}
