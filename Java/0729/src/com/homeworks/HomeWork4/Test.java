package com.homeworks.HomeWork4;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Test {
    public static void main(String[] args) {
        Vip vip = new Vip();
        addVip addVip = new addVip();
        Scanner scanner = new Scanner(System.in);
        int i=0;
        while (i<4){
            vip.inputVip();
            int a = scanner.nextInt();
            vip.inputScore();
            int b = scanner.nextInt();
            addVip.addVip(a,b);
            i++;
        }
        vip.show();
        addVip.showVipScore();
        vip.inputSame();
        int c = scanner.nextInt();
        addVip.searchVipSore(c);
    }
}
