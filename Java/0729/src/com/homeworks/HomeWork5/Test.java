package com.homeworks.HomeWork5;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Test {
    public static void main(String[] args) {
        int[] nums =  new int[]{10,56,34,67,89,0,0,0,0,0};

        Arrays arrays = new Arrays();
        Scanner scanner = new Scanner(System.in);
        arrays.show(nums);
        System.out.print("请输入你要插入的数：");
        int a =scanner.nextInt();
        System.out.print("请输入你要插入的位置：");
        int b =scanner.nextInt();
        arrays.arrayInsert(nums,b,a);
        arrays.show(nums);



    }

}
