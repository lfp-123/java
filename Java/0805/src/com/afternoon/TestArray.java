package com.afternoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author ${林锋鹏}
 * @Title: TestArray
 * @ProjectName Java
 * @date 2019/8/5 15:05
 */
public class TestArray {
    public static void main(String[] args) {

//        ArrayList<Object> objects = new ArrayList<>();
//
//        objects.add("1");
//        objects.add("我");
//        objects.add("2");
//        objects.add("帅");
//
//        objects.forEach(System.out::print);
//        objects.forEach((aa) -> System.out.println(aa + ""));
        LinkedList<String> strings = new LinkedList<>();
        strings.add("222");
        strings.add("221");
        strings.add("22");
        strings.add("252");
        strings.add("2272");
        for (int i = 0; i <strings.size(); i++) {
            System.out.println(strings.get(i));
        }
        TreeSet<String> strings1 = new TreeSet<>();
        strings1.add("333");
        strings1.add("533");
        strings1.add("633");
        strings1.add("733");
        strings1.add("833");
        strings1.add("333");
        strings1.iterator();
    }
}
