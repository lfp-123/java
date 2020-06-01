package com.sc.homework;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/11/11 20:29
 */
public class Test1 {
    Scanner input =  new Scanner(System.in);
    public  void getScore(){
        int count = 0;
        while (true){
            count++;
            System.out.println("您正在玩第"+count+"把游戏");
            int i = input.nextInt();
            System.out.println("成绩是："+i);
            if(i>=60){
                System.out.println("恭喜你进入下一局游戏");
            }else {
                System.out.println("对不起，你的成绩不足于进入下一轮。");
                break;
            }
        }
    }
    public static void  start(){

        System.out.println("请选择你喜欢的游戏：");
        System.out.println("*******************");
        System.out.println("1,斗地主");
        System.out.println("2,斗牛");
        System.out.println("3,泡泡龙");
        System.out.println("4,连连看");
        System.out.println("*******************");

    }

    public  void  playHouse(){
        start();
       aa: while (true){
            System.out.println("请输入你要玩的游戏项目：");
            int i = input.nextInt();
            switch (i){
                case 1 :
                    System.out.println("你已进入斗地主房间");
                    getScore();
                    break aa;
                case 2:
                    System.out.println("你已进入斗牛房间");
                    getScore();
                    break aa;

                case 3:
                    System.out.println("你已进入泡泡龙房间");
                    getScore();
                    break aa;
                case 4:
                    System.out.println("你已进入连连看房间");
                    getScore();
                    break aa;
                default:
                    System.out.println("请重新输入：");

            }
        }
    }
    public void charge(){
       aa: while (true){
            System.out.println("请选择你玩的游戏类型：");
            System.out.println("1,牌类 2,休闲竞技类");
            int i = input.nextInt();
            System.out.println("请输入你的游戏时长：");
            int i1 = input.nextInt();
            switch (i){
                case 1:
                    if(i1>10){
                    System.out.println("你选择的是牌类游戏，时长是：" +
                            ""+i1+"小时，享受五折优惠，你需要支付"+(i1*10*0.5)+"人名币");
                    break aa;
                }else {
                    System.out.println("你选择的是牌类游戏，时长是：" +
                            ""+i1+"小时，享受八折优惠，你需要支付"+(i1*10*0.8)+"人名币");
                    break aa;
                }
                case 2:
                    if(i1>10){
                    System.out.println("你选择的是休闲竞技类游戏，时长是：" +
                            ""+i1+"小时，享受五折优惠，你需要支付"+(i1*20*0.5)+"人名币");
                    break aa;
                }else {
                    System.out.println("你选择的是休闲竞技类游戏，时长是：" +
                            ""+i1+"小时，享受八折优惠，你需要支付"+(i1*20*0.8)+"人名币");
                    break aa;
                }
                default:
                    System.out.println("输入有误，请重新输入：");
            }
        }
    }
    public void click(){
        double count =0 ;
            for (int i = 0; i < 4; i++) {
                System.out.println("点击第"+(i+1)+"个游戏的次数");
                int i1 = input.nextInt();
                if(i1>100){
                    count++;

                }
        }
        System.out.println("点击大于100的游戏数为："+count);
        System.out.println("占比"+count/4);
    }
    public void inputUser(){
        String next = "";
        int age = 0;
        aa: while (true){
            System.out.println("请输入用户的数量：");
            int i = input.nextInt();
            for (int i1 = 0; i1 < i; i1++) {
                System.out.print("请输入编号：");
                     next = input.next();
                System.out.println("请输入年龄：");
                     age =  input.nextInt();
                if(age<18){
                    System.out.println("录入失败，未成年！");
                    break aa;
                }

            }
            System.out.println("编号："+next);
            System.out.println("年龄："+age);
        }

    }
}
