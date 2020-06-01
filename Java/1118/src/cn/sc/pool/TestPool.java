package cn.sc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @author ${林锋鹏}
 * @Title: TestPool
 * @ProjectName Java
 * @Description:
 * @date 2019/11/18 14:34*/


public class TestPool {
    /*可缓存线程池：当使用时可以在线程种取出线程，当用完以后可以再回去
    * 当长时间不使用之后会自动回收，如果线程数量不够，还会自动创建新的线程
    * 对应的方法，会创建对应的线程池
    * ExecutorService 是所有线程池的父类
    * 线程池的执行逻辑：
    * 1，可缓存线程池：空间可以无限大
    *
    */
    public static void main(String[] args) {
      ExecutorService exc =Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            exc.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name);
                }
            });
        }
        /*2 可定长线程池，会定义长度固定，支持最高并发数量
            如果超过了数量，会进入等待
         */
        ExecutorService exf = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            exf.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name);
                }
            });
        }
    /*3,可周期定长线程池
        定一个长度固定的，支持最高并发量
        如果都过了数量就会进入等待，还支持周期操作，延迟等待
     */
        ScheduledExecutorService ecd = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            ecd.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name);
                }
            });
        }


        /*单线程池

         */
        ExecutorService ecs = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            ecs.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name);
                }
            });
        }
    }
}
