package com.homeworks.HomeWork2;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class CheckMoney {
    int money;
    public void checkMoney(int moneys){
        if(moneys%50==0){
            money = moneys+money;
            System.out.println("存款成功");
            System.out.println("当前金额为："+money);
            }else {
            System.out.println("存款失败！请输入正确的数字");
        }


    }
    public  void outMoneys(int moneys){
        if(money%50==0&&money>moneys){
            money = money-moneys;
            System.out.println("取款成功");
            System.out.println("当前金额为："+money);
        }else{
            System.out.println("取款失败！");
        }
    }
}
