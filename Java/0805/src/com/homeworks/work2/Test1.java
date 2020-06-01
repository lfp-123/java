package com.homeworks.work2;

import java.util.ArrayList;

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
        ArrayList dogs = new ArrayList();
        dogs.add(ououDog);
        dogs.add(yayaDog);
        dogs.add(meimeiDog);
        dogs.add(2,feiDog);

        System.out.println("删除前一共有这些狗：");

        System.out.println("分别是：");
        for (int i = 0; i <dogs.size() ; i++) {
            Dog dog =(Dog) dogs.get(i);
            System.out.println(dog.getId()+" "+dog.getName());
        }
        System.out.println("删除其中的部分：");
        dogs.remove(2);
        dogs.remove(1);
        System.out.println("删除后还剩：");
        for (int i = 0; i <dogs.size() ; i++) {
        Dog dogss  =   (Dog)dogs.get(i);
            System.out.println(dogss.getId()+" "+dogss.getName());
        }
        System.out.println("查看是否存在美美?");
        if(dogs.contains(meimeiDog)){
            System.out.println("存在美美");
        }else {
            System.out.println("不存在美美");
        }




    }
}
