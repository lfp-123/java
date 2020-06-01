package com.seecen.work2;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ${林锋鹏}
 * @Title: ReadWrite
 * @ProjectName Java
 * @date 2019/8/13 11:38
 */
public class ReadWrite {
        String name;
        ReadWriteLock Lock = new ReentrantReadWriteLock();
        public void readName(){
            Lock  readLock=   this.Lock.readLock();
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"进入read："+name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"准备结束read："+name);
            readLock.unlock();
        }
            public void writeName(){
                Lock writeLock = this.Lock.writeLock();
                writeLock.lock();

                String rondomname =      (char)(Math.random()*100)+"";
                System.out.println(Thread.currentThread().getName()+"正在赋值："+rondomname);
                this.name = rondomname;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("赋值完毕!结果为："+name);
                writeLock.unlock();
            }
}
