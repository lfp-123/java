package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/14 10:37
 */
public class Test {
    public static void main(String[] args) {

        MyThread mythread = new MyThread();

            mythread.start();
        try {
            Thread.sleep(10);
            mythread.flag=true;
            System.out.println("end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
