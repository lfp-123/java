package com.homeworks.HomeWork4;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Vip {
    int id ;
    int score;
    public void inputVip(){
        System.out.print("输入会员编号：");
    }
    public void inputScore(){
        System.out.print("输入积分：");
    }
    public  void inputSame(){ System.out.print("请输入你要查找的会员号："); }
    public void show(){
        System.out.println("***会员列表****");
    }
}
