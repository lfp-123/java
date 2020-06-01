package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: NewThread
 * @ProjectName Java
 * @date 2019/8/12 15:12
 */
public class NewThread {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread threads = new Thread(myRunnable);
        threads.start();
        while (true) {

            System.out.println("main.....");
        }

    }
}
