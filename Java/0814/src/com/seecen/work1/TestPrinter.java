package com.seecen.work1;

/**
 * @Author 林锋鹏
 * @Date 2019/8/12 16:44
 * @Description
 */
public class TestPrinter {
    public static Integer num = 10;

    public static void main(String[] args) {
        Printer printer = new Printer();
        MyPrinterThread a = new MyPrinterThread("aaaaaaaaaaaaaaaaaaaa", printer);
        MyPrinterThread b = new MyPrinterThread("bbbbbbbbbbbbbbbbbbbb", printer);

        new Thread(a).start();
        new Thread(b).start();
    }
}
