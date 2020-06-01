package com.homework.HomeWork1;


import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/26 19:05
 */
public class TestCards {

    public static void main(String[] args) {
        Cards cards1 = new Cards();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入你积分：");
        int score = scanner.nextInt();
        cards1.score= score;

        System.out.print("请输入你会员卡的类型（金卡/普卡）：");
        String cards = scanner.next();
        cards1.card = cards;

        cards1.show();
    }
}
