package com.homeworks;

/**
 * @author ${林锋鹏}
 * @Title: TestThreadDemo2
 * @ProjectName Java
 * @date 2019/8/9 19:28
 */
public class TestThreadDemo2 {
    public static void main(String[] args) {
        Runnable thread1 = new Runnable("thread1");
        Runnable thread2 = new Runnable("thread2");
        thread1.start();
        thread2.start();
    }
}
class Runnable extends Thread{
        private Thread T;
        private String threadName;

    Runnable(String threadName) {
        this.threadName = threadName;
    }


    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    @Override
    public synchronized void start() {
        System.out.println("start" + threadName);
        if (T == null) {
            T = new Thread(this, threadName);
            T.start();
        }
    }
}