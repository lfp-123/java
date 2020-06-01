package cn.sc.mor;

import java.io.ObjectInputStream;

/**
 * @author ${林锋鹏}
 * @Title: TestLock
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/18 14:18
 */
public class TestLock implements Runnable {
    Object o1 =  new Object();
    Object o2 =  new Object();
    boolean h;
    @Override
    public void run() {
        if(h){
            synchronized (o1){
                System.out.println("线程1开始执行，休息0.5s开始执行o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("o2被锁住了");
                }
            }

        }else {
            synchronized (o2){
                System.out.println("线程2开始执行，休息0.5开始执行o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("o1被锁住了");
                }
            }
        }

    }

    public static void main(String[] args) {
        TestLock testLock1 = new TestLock();
        testLock1.h=false;
        TestLock testLock = testLock1;
        testLock.h=true;
        Thread t1 = new Thread(testLock);
        Thread t2 = new Thread(testLock1);
        t1.start();
        t2.start();
    }
}
