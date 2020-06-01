package com.seencen.afternoon.work4;

/**
 * @author asus
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 15:24
 */
public class Test {
    public static void main(String[] args) {

        Inkbox brownInk = new BrownInk();
        Paper B5 = new B5();
        Print print = new Print(brownInk,B5);
        print.show("你真好看！");
        Paper A4 = new A4();
        Inkbox colorbox = new ColorBox();
        Print print1 =   new Print(colorbox,A4);
        print1.show("JAVA面试宝典");
    }
}
