package com.seecen.afternoon;

/**
 * @author ${林锋鹏}
 * @Title: TestPrinter
 * @ProjectName Java
 * @date 2019/8/12 17:16
 */
public class TestPrinter {
    public static void main(String[] args) {

//        Printer printer = new Printer();
//        printer.setName("aaaaaaa");
//        printer.printName();
        Printer printer = new Printer();
        Threads threads1 = new Threads("aaaaaaaaaaaaaaaaaa",printer);
        Threads threads2 = new Threads("bbbbbbbbbbbbbbbbbb",printer);
        new Thread(threads1).start();
        new Thread(threads2).start();

    }
}
