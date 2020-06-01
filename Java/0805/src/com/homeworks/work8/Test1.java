package com.homeworks.work8;

import java.util.ArrayList;
import java.util.Iterator;

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
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        dogs.add(ououDog);
        dogs.add(yayaDog);
        dogs.add(meimeiDog);
        dogs.add(2,feiDog);

        System.out.println("一共有："+dogs.size()+"条狗");

        System.out.println("使用iterator遍历分别是：");
        Iterator<Dog> dos = dogs.iterator();
        while (dos.hasNext()){
            Dog next = dos.next();
            System.out.println("id"+next.getId()+" name:"+next.getName());
        }



    }
}
