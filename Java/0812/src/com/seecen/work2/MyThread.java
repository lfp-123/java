package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: Mythread
 * @ProjectName Java
 * @date 2019/8/12 14:55
 */
public class MyThread  extends  Thread{
    @Override
    public void run() {
        while (true) {
            System.out.println("run"+Thread.currentThread().getId());
        }
    }
}
