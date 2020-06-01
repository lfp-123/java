package com.seecen.dd.service;

import com.seecen.dd.cons.CardUntil;
import com.seecen.dd.entity.MobileCard;
import com.seecen.dd.entity.ServicePackage;


import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author ${林锋鹏}
 * @Title: TestMain
 * @ProjectName Java
 * @date 2019/8/15 17:44
 */
    public class TestMain {
    ServicePackage service =null;
    Scanner input = new Scanner(System.in);
    CardUntil cardUntil= new CardUntil();
    MobileCard card =  new MobileCard();

    static int count;
     MyThread my =new MyThread();
    public static void main(String[] args) {
        Timer times = new Timer();
        TestMain testMain = new TestMain();
     // times.schedule(new MyThread(),1000*5,15*1000);
        testMain.start();

    }
    public void start(){
        Main:while (true) {
            System.out.println("*******欢迎使用兜兜5G大厅********");
            System.out.println("1.用户登陆\t2.用户注册\t3.使用兜兜\t4.话费充值\t5.资费说明\t6.退出系统");
            System.out.print("请选择：");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    cardMenu(); // 登陆
                    continue ;
                case 2:
                    registration(); //用户注册 选择套餐
                    continue ;
                case 3:
                    CardUntil.init();//反序列化对象
                    System.out.print("请输入手机号:");
                    String phonenumber= input.next();
                    if(cardUntil.isExistCard(phonenumber)) {
                        System.out.println("该号码不存在！");
                    }else {
                        cardUntil.useDD(phonenumber);
                    }
                    break;
                case 4:
                    CardUntil.init();//反序列化对象
                    System.out.print("请输入手机号:");
                    String number= input.next();
                    if(cardUntil.isExistCard(number)) {
                        System.out.println("该号码不存在！");
                    }else {
                        System.out.print("请输入你要充值的金额： ");
                        int money = input.nextInt();
                        cardUntil.reCharge(number,money);
                        System.out.println("充值成功！");
                    }
                    break;
                case 5:
                    //查看资费说明
                    cardUntil.showDescription();
                    break;
                default:
                    break Main;
            }
        }
    }
    //登陆注册
    public void registration () {
        System.out.println("******可以选择的卡号****");
        String[] newNumbers = cardUntil.getNewNumbers();
        for (int i = 0; i < newNumbers.length - 1; i++) {
            System.out.print((i + 1) + "." + "139" + newNumbers[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.print("请输入卡号（输入卡号的序号）：");
        int key = input.nextInt();
        while (true) {
            if (key >= 1 && key <= 9) {
                card.setCardNumber("139"+newNumbers[key - 1]);
                break;
            } else {
                System.out.println("请重新输入：");
            }
        }
        System.out.print("1.话痨套餐\t2.网虫套餐\t3.超人套餐(输入序号：)");
        service = cardUntil.creatServicePackage();
        System.out.print("请输入姓名：");
        String username = input.next();

        System.out.print("请输入密码：");
        String password = input.next();

        System.out.print("请输入要预存的话费：");
        double money = input.nextDouble();
      //返回一个包类型

        card.setSerPackage(service);
        while (true) {
            if (money - service.getPrice() < 0) {
                System.out.print("您选择预存的花费不足以支付本次套餐，请重新充值：");
                money = input.nextInt();
            }else {
                break;
            }
        }
        card.setUserName(username);
        card.setMoney(money - service.getPrice());
        System.out.print("注册成功！");
        MobileCard newCard = new MobileCard(card.getCardNumber(),card.getUserName(),password,service,card.getMoney());
        cardUntil.addCard(newCard.getCardNumber(),newCard);
        System.out.println(newCard.getUserName());
        card.showMeg();
        new Thread(new MyMessage(newCard.getCardNumber())).start();
    }
    /*
    二级菜单
     */
    public void cardMenu(){
        CardUntil.init();//反序列化对象
        boolean etc = true;
        System.out.println("输入手机号：");
        String phoneNumber = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        if (cardUntil.isExistCard(phoneNumber,password)){
            System.out.println("登陆成功！");
        }else{
            if(count==3) {
                try {
                    System.out.println("密码错误，请5s后请重试：");
                    Thread.sleep(5000);
                    count =0;
                    return;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println("登陆失败，您还有"+(3-count)+"次机会！");
            return;
        }

        do {
            System.out.println("*******兜兜移动用户菜单*****");
            System.out.println("1.本月用户账单查询" +
                    "\n2.套餐余额查询\n" +
                    "3.打印消费查询\n" +
                    "4.套餐变更办理\n" +
                    "5.退网\n" +
                    "请选择（输入1-5选择功能，按其他键返回上一级）：");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    cardUntil.showAmountDetail(phoneNumber);
                    //本月查询
                    break;
                case 2:
                    cardUntil.showRemainDetail(phoneNumber);//余额查询
                    break;
                case 3:
                    cardUntil.printAmountDetail(phoneNumber); //打印消费查询
                    break;
                case 4:
                    cardUntil.chargingPack(phoneNumber); //变更套餐
                    break;
                case 5:
                    cardUntil.delCard(phoneNumber); //销户
                    break;
                default:
                    new TestMain().start();
                    etc =false;
                    break;

            }
        }while (etc);
    }
}

