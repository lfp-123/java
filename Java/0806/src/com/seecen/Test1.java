package com.seecen;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/6 15:15
 */
public class Test1 {
    public static void main(String[] args) throws Throwable {
        Student stu= new Student("刘备", 20);
        stu=null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(stu.getUsername());


    }
}
