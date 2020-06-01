package com.seecen.work3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ${林锋鹏}
 * @Title: ScheduledPool
 * @ProjectName Java
 * @date 2019/8/12 16:29
 */
public class ScheduledPool {
    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println("读取Excel..." + Thread.currentThread().getId());
        };
        ScheduledExecutorService exector = Executors.newScheduledThreadPool(2);
        exector.scheduleAtFixedRate(r,5000,1000, TimeUnit.MICROSECONDS);
        exector.shutdown();
    }
}
