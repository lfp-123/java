package com.afternoon;

import java.util.ArrayList;

/**
 * @Author bigpig
 * @Date 2019/8/5 11:23
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        BigpigList list = new BigpigList();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        System.out.println(list.size());
        list.remove(3);
        System.out.println(list.get(4));




    }
}
