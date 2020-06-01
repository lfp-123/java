package com.seecen.work1;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ${林锋鹏}
 * @Title: Printer
 * @ProjectName Java
 * @date 2019/8/13 10:03
 */
public class Printer {
    Lock lock = new ReentrantLock();
    public   void setName(){
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("name.. start.." + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("name的else" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        }

    public   void setName2(){

        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("name222222.. start.." + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("name222222的else" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
