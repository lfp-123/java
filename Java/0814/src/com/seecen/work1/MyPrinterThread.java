package com.seecen.work1;

/**
 * @Author 林锋鹏
 * @Date 2019/8/12 16:45
 * @Description
 */
public class MyPrinterThread implements Runnable {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private String userName;
    private Printer printer;
    public MyPrinterThread(String userName, Printer printer) {
        this.userName = userName;

        this.printer = printer;
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if(threadLocal.get() == null){
                threadLocal.set(userName);
            }
            printer.printerName();
        }
    }
}
