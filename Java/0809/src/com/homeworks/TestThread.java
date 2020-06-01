package com.homeworks;

import java.lang.Runnable;

/**
 * @author ${林锋鹏}
 * @Title: TestThread
 * @ProjectName Java
 * @date 2019/8/9 19:15
 */
public class TestThread {
    public static void main(String[] args) {
        RunnableDemo r1 = new RunnableDemo("thread1");
        RunnableDemo r2 = new RunnableDemo("thread2");
        r1.start();
        r2.start();
    }
}

class RunnableDemo implements Runnable{
        private Thread t;
        private String threadName;

        public RunnableDemo(String name) {
           threadName = name;
            System.out.println("Creating"+threadName );
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

        public  void start(){
        System.out.println("start"+threadName);
        if(t==null){
            t= new Thread(this,threadName);
            t.start();
        }
        }

    }

