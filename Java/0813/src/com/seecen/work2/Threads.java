package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: Threads
 * @ProjectName Java
 * @date 2019/8/13 11:44
 */
public class Threads extends Thread {

    private ReadWrite readWrite;

    public Threads(String threadName,ReadWrite readWrite) {
        super(threadName);
        this.readWrite = readWrite;
    }


    @Override
    public void run() {
    while (true){
//        int num = (int)(Math.random()*100);
//        if()if
        readWrite.readName();
        readWrite.writeName();
    }
    }
}
