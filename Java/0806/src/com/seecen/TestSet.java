package com.seecen;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author bigpig
 * @Date 2019/8/6 14:24
 * @Description
 */
public class TestSet {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();
        Student stu1 = new Student("刘备", 10000);
        Student stu2 = new Student("刘备", 10000);
        System.out.println(stu1 == stu2);
        System.out.println(stu1.equals(stu2));
        set.add(stu1);
        System.out.println(stu1.hashCode());
        System.out.println(stu2.hashCode());
        System.out.println(set.size()); // 1
        set.remove(stu2);
        System.out.println(set.size()); // 0
    }
}
