package com.seecen.work3;

/**
 * @author ${林锋鹏}
 * @Title: MyThread
 * @ProjectName Java
 * @date 2019/8/14 11:34
 */
public class MyThread extends Thread {
    @Override
    public void run(){
        while (true){
            synchronized (MyThread.class) {
                System.out.println(Thread.currentThread().getName() + "start");
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + "end");
            }
        }

    }
}
