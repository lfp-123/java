package com.seecen.work4;



/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/14 15:45
 */
public class Test  {
    public static void main(String[] args) {

        Printer printer = new Printer();
       Runnable r1 = ()->{
           try {
               printer.printerNum();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       };
       Runnable r2 = ()->{
           try {
               printer.printWord();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       };




    new Thread(r1).start();
    new Thread(r2).start();
    }



}
