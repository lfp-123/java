package com.homeworks.homework1;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class Test {
    public static void main(String[] args) {
    Printer  p =new ColorPrinter();
    p.print("文本");
    p = new BlackColor();
    p.print("文本");

    }
}
