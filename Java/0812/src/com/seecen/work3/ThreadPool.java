package com.seecen.work3;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool {
    public static void main(String[] args) throws Exception {
        // lambda表达式创建匿名实现类对象(必须是接口且只有一个方法)
        Runnable r = () -> System.out.println("读取excel...." + Thread.currentThread().getId());
        /* 线程池,有四种
            第一种CachePool
        1. 没有线程的时候,创建一个新的线程,使用完后放回
        池中
        2. 下次调用会重复利用该池中的线程,而不需要重新创建
        新的线程
        3. 如果池中没有线程, 新的任务会新建一个线程
        */
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            ExecutorService executorService = Executors.newCachedThreadPool();
            // 启动线程
            executorService.execute(r);
            executorService.shutdown();
        }




    }

}
