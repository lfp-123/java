package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/13 11:48
 */
public class Test {
    public static void main(String[] args) {
        ReadWrite readWrite = new ReadWrite();
        Threads threads = new Threads("A线程",readWrite);
        Threads threads2 = new Threads("B线程",readWrite);
    threads.start();
    threads2.start();

    }
    public void run(){
        return;
    }
}
