package com.homeworks.homework2;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class Monkey extends  Animals{
    private  int  iq;


    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    @Override
    void introduce() {
        System.out.println("我是一只猴子"+"我的年龄是："+this.age+"我的智商为："+this.iq);
    }
}
