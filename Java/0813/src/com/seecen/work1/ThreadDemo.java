package com.seecen.work1;

/**
 * @author ${林锋鹏}
 * @Title: ThreadDemo
 * @ProjectName Java
 * @date 2019/8/13 10:08
 */
public class ThreadDemo implements Runnable{
    Printer printer;
    public ThreadDemo(Printer printer){
        this.printer = printer;
    }


    @Override
    public void run() {
        while (true){
            printer.setName();
            printer.setName2();
        }
    }
}
