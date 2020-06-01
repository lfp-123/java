package com.seecen.work6;

import java.util.LinkedList;

/**
 * @author ${林锋鹏}
 * @Title: WareHouse
 * @ProjectName Java
 * @date 2019/8/14 17:01
 */
public class Warehouse {
    public static String FOODNAME = "刀"; //生产的物品
    public static int max =10;
    LinkedList<String> foods=   new LinkedList<>(); //容器
    public synchronized void producer(String userName)throws InterruptedException{
        while (foods.size()>10){
            this.wait();
        }
        if(foods.size()>0){
            this.notifyAll();
        }
        String food =( Math.random()*100)+"型号"+FOODNAME;
        foods.add(food);
        System.out.println(userName+"生产了"+food+"库存还有："+foods.size());
        Thread.sleep(500);
    }

    public synchronized void consumption(String userName) throws InterruptedException{
        while (foods.size()==0){
            this.wait();
        }
        if(foods.size()>0){
            this.notifyAll();
        }
        String con1=foods.get(0);
        foods.removeFirst();
        System.out.println("\t\t"+userName+"消费了:"+con1+"剩余库存"+foods.size());

    }
}
