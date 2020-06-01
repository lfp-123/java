package com.homeworks.HomeWork3;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        addVip addVip = new addVip();
        Vip vip = new Vip();
        vip.inputVip();
        int a = scanner.nextInt();
        vip.inputScore();
        int b = scanner.nextInt();
        addVip.addVip(a,b);
        vip.show();
        addVip.showVipScore();

    }
}
