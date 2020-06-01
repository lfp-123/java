package com.seecen.work3;

import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ${林锋鹏}
 * @Title: FiexdPool
 * @ProjectName Java
 * @date 2019/8/12 16:23
 */
public class FiexdPool {
    public static void main(String[] args) {

        Runnable r = () -> {
            System.out.println("读取Excel..." + Thread.currentThread().getId());
        };

       ExecutorService  executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            executorService.execute(r);
        }
        executorService.shutdown();
        System.out.println("end");

    }
}

