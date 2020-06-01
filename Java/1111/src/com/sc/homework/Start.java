package com.sc.homework;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: start
 * @ProjectName Java
 * @date 2019/11/11 21:32
 */
public class Start{
   Test1 test= new Test1();
   Scanner input = new Scanner(System.in);


   public static void main(String[] args) {
        new Start().show();

    }
    public  void show(){
        while (true){
            System.out.println("欢迎进入青鸟游戏平台");
            System.out.println("*************");
            System.out.println("1,玩游戏");
            System.out.println("2,冲游戏币");
            System.out.println("3,游戏点击率");
            System.out.println("4,添加用户信息");
            System.out.println("*************");
            System.out.println("请输入你要选择的项目：");
            int i = input.nextInt();
            switch (i){
                case 1 :
                    test.playHouse();
                    break;
                case 2 :
                    test.charge();
                    break;
                case 3:
                    test.click();
                    break;
                case 4:
                    test.inputUser();
                    break;
                default:
                    System.out.println("输入错误请重新输入：");
            }
        }
    }
    }


