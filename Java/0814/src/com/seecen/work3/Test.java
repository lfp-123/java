package com.seecen.work3;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/14 11:39
 */
public class Test {
    public static void main(String[] args) {

        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();

    }
}
