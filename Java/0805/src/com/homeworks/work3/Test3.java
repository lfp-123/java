package com.homeworks.work3;

import com.seecen.Parent;

import java.util.ArrayList;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Java
 * @date 2019/8/5 19:16
 */
public class Test3 {
    public static void main(String[] args) {
        Penguin penguin1 = new Penguin("小白", "白企鹅");
        Penguin penguin2 = new Penguin("小黑", "黑企鹅");
        Penguin penguin3 = new Penguin("小蓝", "蓝企鹅");
        Penguin penguin4 = new Penguin("小粉", "粉企鹅");
        Penguin penguin5 = new Penguin("小绿", "绿企鹅");
        ArrayList arrayList = new ArrayList();
        arrayList.add(penguin1);
        arrayList.add(penguin2);
        arrayList.add(penguin3);
        arrayList.add(penguin4);
        arrayList.add(penguin5);

        System.out.println("共有企鹅如下：");
        System.out.println("一共有" +arrayList.size()+"只企鹅。");
        for (int i = 0; i <arrayList.size() ; i++) {
            Penguin penguin=   (Penguin)arrayList.get(i);
            System.out.println(penguin.getName() +" " +penguin.getId());
        }
        System.out.println("小黑太丑了，准备把他去除！");
        arrayList.remove(1);
        System.out.println("删除后一共有" +arrayList.size()+"只企鹅。");
        for (int i = 0; i <arrayList.size() ; i++) {
            Penguin penguin=   (Penguin)arrayList.get(i);
            System.out.println(penguin.getName() +" " +penguin.getId());
        }
        System.out.println("找一下小白！");
        if(arrayList.contains(penguin1)){
            System.out.println("找到小白！");
        }else {
            System.out.println("没有小白！"
            );
        }
    }
}
