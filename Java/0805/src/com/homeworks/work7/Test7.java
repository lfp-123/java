package com.homeworks.work7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Java
 * @date 2019/8/5 19:16
 */
public class Test7 {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("白企鹅");
        arrayList.add("黑企鹅");
        arrayList.add("蓝企鹅");
        arrayList.add("粉企鹅");
        arrayList.add("绿企鹅");
        arrayList.add("黄企鹅");

        LinkedList linkedList = new LinkedList();
        linkedList.add("一只企鹅");
        linkedList.add("两只企鹅");
        linkedList.add("三只企鹅");
        linkedList.add("四只企鹅");
        linkedList.add("五只企鹅");
        linkedList.add("六只企鹅");


        System.out.println("iterator遍历");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Iterator iterators=  linkedList.iterator();
        while (iterators.hasNext()){
            System.out.println(iterators.next());
        }
    }
}