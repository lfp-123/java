package com.sc.homework.简答题;

/**
 * @author asus
 * @Title: TestPrint
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/1219:55
 */
public class TestPrint {
    public static void main(String[] args) {
        Printer blackPrint = new BlackPrint();
        blackPrint.print();
        Printer colorPrint = new ColorPrint();
        colorPrint.print();
    }
}
