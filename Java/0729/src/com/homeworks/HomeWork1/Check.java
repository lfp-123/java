package com.homeworks.HomeWork1;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Check {
    public void check(String name,String password){
        if(name.equals("JadeBird")&&password.equals("0000")){
            System.out.println("登陆成功！");
            System.out.println("********我行我素购物系统****");
            System.out.println("*************************");
        }else {
            System.out.println("您没有权限进入此系统！");
        }
    }
}
