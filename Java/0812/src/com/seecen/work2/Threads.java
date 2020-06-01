package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: Thread
 * @ProjectName Java
 * @date 2019/8/12 14:53
 */
public class Threads extends Thread {
    public static void main(String[] args)  {
            MyThread thread = new MyThread();
            thread.start();
            while (true){
            System.out.println("444444main"+Thread.currentThread().getId());
            }

    }
}
