package com.seecen.work5;



/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/14 16:11
 */
public class Test {
    public static void main(String[] args) {

        Printer printer = new Printer();
        MyThread my1 = new MyThread("赵云",  printer);
        MyThread my2 = new MyThread("关羽",  printer);
        my1.start();
        my2.start();
    }
}
