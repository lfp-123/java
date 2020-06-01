package com;

/**
 * @author asus
 * @date 2019/7/26 15:20
 */
public class TestVisitor {

    public static void main(String[] args) {
        Visitor liming = new Visitor();
        liming.age =22;
        liming.name ="李明";
        liming.showPrice();

        Visitor sun = new Visitor();
        sun.age =90;
        sun.name ="孙悟空";
        sun.showPrice();

    }
}