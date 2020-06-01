package com.homeworks.work4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/5 18:53
 */
public class Test1 {
    public static void main(String[] args) {
        Dog ououDog = new Dog("欧欧", "雪纳瑞");
        Dog yayaDog = new Dog("亚亚", "拉布拉多");
        Dog meimeiDog = new Dog("美美", "雪纳瑞");
        Dog feiDog = new Dog("菲菲", "拉布拉多");
      LinkedList dogs = new LinkedList();
        dogs.addFirst(ououDog);

        dogs.addLast(meimeiDog);



        System.out.println("一共有："+dogs.size()+"条狗");
        Dog firstDog = (Dog)dogs.getFirst();
        System.out.println("第一条的狗是："+firstDog.getId());
        Dog lastDog = (Dog)dogs.getLast();
        System.out.println("第一条的狗是："+lastDog.getId());


    }
}
