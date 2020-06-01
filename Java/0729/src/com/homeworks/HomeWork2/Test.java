package com.homeworks.HomeWork2;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Test {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        CheckMoney checkMoney = new CheckMoney();
       aa: while (true) {
            menu.show();
            menu.inputNumberShow();
            int num = scanner.nextInt();
            switch (num){
                case 1:
                    menu.inputMoney();
                    int money= scanner.nextInt();
                    checkMoney.checkMoney(money);
                    break;
                case 2:
                    menu.OutMoney();
                    int moneys = scanner.nextInt();
                    checkMoney.outMoneys(moneys);
                    break;
                default:
                     break aa;
                }
            }
        }
    }

