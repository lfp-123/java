package com.seecen;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/8 10:39
 */
public class Test1 {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1(){
            @Override
            public void add() {
                System.out.println("hello world");
            }

            @Override
            public void delete() {

            }
        };
        demo1.add();

        Demo11 demo11 = new Demo11(){
            @Override
            public void add() {
                System.out.println("hello");
            }
        };
        demo11.add();

        Demo11 demo111 =()-> System.out.println("hhhhh");
        demo111.add();
        System.out.println("");
    }
}
