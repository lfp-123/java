package com.sc.morning;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Test2
 * @ProjectName Java
 * @date 2019/11/11 11:21
 */
public class Test2 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        double count=0;
        double total=5;
        double rate =0.00;
        for (int i = 0; i < 5; i++) {
            System.out.println("请输入第"+(i+1)+"个员工的年龄");
            int i1 = scanner.nextInt();
            if (i1>30){
                count++;
            }
        }
        System.out.println("30岁以上的员工的占比："+count/total);
    }
}
