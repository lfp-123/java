package com.sc.homework;

import java.util.Random;
import java.util.Scanner;


public class Test {
    static Scanner sc=new Scanner(System.in);
    static User[]  userList=new User[10];
    static User u;
    static double[] randoms = new double[5];
    public static void main(String[] args) {
        String yn;
    do {
        System.out.println("*****富翁系统*****");
        System.out.println("1.注册");
        System.out.println("2.登录");
        System.out.println("3.抽奖");
        System.out.println("******************");
        System.out.println("请输入选择的菜单");
        int n = sc.nextInt();
        switch (n) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;//登录
            case 3:
                start();
                break;//抽奖
            default:
                System.out.println("输入有误，请重新输入");
                break;
        }
        System.out.print("是否继续y/n：");
        yn = sc.next();
    }while (yn.equals("y"));
    }
    public static void register(){  //注册
        System.out.println("[奖客富翁系统 > 注册 ]");
        System.out.println("请输入个人注册信息");
        System.out.print("用户名:");
        String username=sc.next();
        System.out.print("密码:");
        String password=sc.next();
        //随机生成四位会员卡号   1000~9999
        Random r=new Random();
        String card=r.nextInt(9000)+1000+"";
        User u=new User(username,password,card);
        for(int i=0;i<userList.length;i++){
            if(userList[i]==null){
                userList[i]=u;
                break;
            }
        }
        System.out.println("注册成功"+",你的账户信息："+"账号："
                +u.getUsername()+"密码： "+u.getPassword()+"卡号： "+u.getCard());
    }
    public static void login(){
        System.out.println("[奖客富翁系统 > 登陆 ]");//登录
        System.out.println("请输入账号：");
        String us = sc.next();
        System.out.println("请输入密码：");
        String pw = sc.next();
        for (User users:
            userList ) {
            if (users!=null) {
                if (users.getUsername().equals(us) && users.getPassword().equals(pw)) {
                    System.out.println("登陆成功！！");
                        u =  users;
                    break;
                }
            }else{
                System.out.print("登陆失败！！");
                break;
            }
        }
    }
    public static void start(){  //抽奖
        System.out.println("[奖客富翁系统 > 抽奖 ]");
        boolean index = false;
        System.out.println("今天的幸运数字");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
           int num= random.nextInt(9000)+1000;
           randoms[i]=num;
           System.out.print(num+" ");
        }
        for (double numss:
                randoms) {
            if ((numss + "").equals(u.getCard())) {
                index = true;
            }
        }
        if (index){
            System.out.println("恭喜你抽奖成功！");
        }else {
            System.out.println("对不起抽奖失败！");
        }
    }
}
