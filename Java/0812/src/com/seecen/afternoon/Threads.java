package com.seecen.afternoon;

/**
 * @author ${林锋鹏}
 * @Title: Thread
 * @ProjectName Java
 * @date 2019/8/12 17:19
 */
public class Threads implements Runnable  {
    private  String userName;
    private  Printer printer;

    public Threads(String userName, Printer printer) {
        this.userName = userName;
        this.printer= printer;
    }



    @Override
    public void  run() {
        for (int i = 0; i <500 ; i++) {
            synchronized (  printer) {
                printer.setName(this.userName);
                printer.printName();
            }
        }

    }
}
