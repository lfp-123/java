package com.seecen.work1;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/13 10:10
 */
public class Test {
    public static void main(String[] args) {

        Printer printer = new Printer();
        ThreadDemo threadDemo1 = new ThreadDemo(printer);
        ThreadDemo threadDemo2 = new ThreadDemo(printer);
        Thread thread = new Thread(threadDemo1,"A");
        Thread thread2 = new Thread(threadDemo2,"B");
        thread.start();
        thread2.start();


    }
}
