package com.homework;

/**
 * @author asus
 * @date 2019/7/28 11:22
 */
public class Animals {
    String name;
    int id;

    public Animals(String myname,int myid){
        name =myname;
        id= myid;

    }

    public  void eat(){
        System.out.println(name+"在吃~！");
    }
    public  void sleep(){
        System.out.println(name+"我在睡觉");
    }
    public  void introduce(){
        System.out.println("我是:"+name+"我的id为："+id);
    }
}
