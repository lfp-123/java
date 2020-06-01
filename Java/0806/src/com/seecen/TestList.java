package com.seecen;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author bigpig
 * @Date 2019/8/6 15:04
 * @Description
 */
public class TestList {
    public static void main(String[] args) {
        List list = new ArrayList();
        Student stu1 = new Student("刘备", 10000);
        Student stu2 = new Student("刘备", 10000);
        list.add(stu1);
        System.out.println(list.size());
        list.remove(stu2);
        System.out.println(list.size());

    }

}
