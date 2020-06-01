package com.seecen.work5;

/**
 * @author ${林锋鹏}
 * @Title: MyThread
 * @ProjectName Java
 * @date 2019/8/14 16:06
 */
public class MyThread extends Thread{
    String username;

    Printer printer;
    public MyThread(String username, Printer printer) {
        this.username = username;

        this.printer = printer;
    }
    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }









    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public void run() {
        try {
            while (true) {
                printer.printer(username);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
