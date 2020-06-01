package com.sc.morning;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/11/11 9:37
 */
public class Test1 {
    public static void main(String[] args) {
        int year = 2000;
        double rate = 0.1;
        double InitialMoney = 1000000;

        while (InitialMoney<=2000000){
            InitialMoney = InitialMoney*rate+InitialMoney;
            year++;
        }
        System.out.println(year);
    }
}
