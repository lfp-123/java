package com.homework.HomeWork3;

import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/26 19:25
 */
public class TestCalculator {
    public static void main(String[] args) {
      int NULL = 12;
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入一个数字：");
        double num1 = scanner.nextDouble();
        calculator.digtal1 = num1;

        System.out.print("请输入一个加减乘除的符号（+ - * /）：");
        String str = scanner.next();
        calculator.symbols = str;

        System.out.print("请输入另一个数字：");
        double num2 = scanner.nextDouble();
        calculator.digtal2=  num2;
        System.out.println(calculator.Calculator());
    }

}
