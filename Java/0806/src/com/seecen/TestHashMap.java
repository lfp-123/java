package com.seecen;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author bigpig
 * @Date 2019/8/6 15:10
 * @Description
 */
public class TestHashMap {
    public static void main(String[] args) {
        Map<Student, String> map = new HashMap();
        Student stu1 = new Student("刘备", 10000);
        Student stu2 = new Student("刘备", 10000);
        map.put(stu1, "hello");
        System.out.println(map.get(stu2));


    }
}
