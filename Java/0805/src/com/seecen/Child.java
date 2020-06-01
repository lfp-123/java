package com.seecen;

/**
 * @author ${林锋鹏}
 * @Title: Chird
 * @ProjectName Java
 * @date 2019/8/5 11:10
 */
public class Child extends Parent {


    Child(){

        System.out.println("子类实例");
    }
    static {
        System.out.println("子类静态");
    }

}
