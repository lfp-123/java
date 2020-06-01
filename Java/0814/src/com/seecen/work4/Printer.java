package com.seecen.work4;

/**
 * @author ${林锋鹏}
 * @Title: Printer
 * @ProjectName Java
 * @date 2019/8/14 14:58
 */
public class Printer {
    public boolean start=false;
    public synchronized void printerNum () throws InterruptedException{
        for (int i = 1; i <=52 ; i++) {
            System.out.print(i);
            if(i%2==0){
                start=true;
                this.notify();
                this.wait();
            }
        }
    }
    public synchronized void printWord() throws InterruptedException{
        if (start) {
            for (int i = 65; i <= 90; i++) {
                System.out.print((char)i);

                this.notify();
                if(!(i==90)) this.wait();
            }
        }
    }
}
