package com.seecen.dd.entity;

import java.io.Serializable;

/**
 * @author ${林锋鹏}
 * @Title: 网虫套餐
 * @ProjectName Java
 * @date 2019/8/15 16:23
 */
public class NetPackage extends ServicePackage implements CallService,SendService, NetService,Serializable {
    private int  flow; //上网流量
    private double price ;
    private int talktime;

    public int getTalktime() {
        return talktime;
    }

    public void setTalktime(int talktime) {
        this.talktime = talktime;
    }

    private int smsCount;

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public NetPackage() {
     this.flow =50;
     this.price = 88;
     this.smsCount=0;
    }


    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void showInfo() {
        System.out.println("网虫套餐："+"流量"+this.flow+
                "，套餐每月资费为："+this.price+"/每月");
    }


    @Override
    public int Net(int flow, MobileCard card) throws Exception {
        int temp = flow;
        for (int i = 0; i < flow; i++) {
            if (this.flow - card.getRealFlow() >= 1) {
                //第一种情况：流量剩余够 1 MB
                card.setRealFlow(card.getRealFlow() + 1);
            } else if (card.getMoney() >= 0.1) {
                //第二种情况：套餐内流量已经用完，剩下话费够支付 1 MB的流量
                card.setRealFlow(card.getRealFlow() + 1); //实际短信数加 1 条
                //账户余额消费0.1元，（1MB流量）
                card.setMoney(card.getMoney() - 1);
                card.setConsumAmout(card.getConsumAmout() +1);  //当月消费金额 + 0.1
            } else {
                temp = i; //记录使用流量多少MB
                throw new Exception("流量已经使用" + i + "MB，您的余额不足，请充值后再使用！");
            }
        }
        return temp;
    }

    @Override
    public int send(int Smscount, MobileCard card) throws Exception{
        int temp = Smscount;
        for (int i = 0; i < Smscount; i++) {
            if (this.smsCount - card.getRealSMSCount() >= 1) {
                //第一种情况：流量剩余够 1 MB
                card.setRealSMSCount(card.getRealSMSCount() + 1);
            } else if (card.getMoney() >= 0.1) {
                //第二种情况：套餐内流量已经用完，剩下话费够支付 1 MB的流量
                card.setRealSMSCount(card.getRealSMSCount() + 1); //实际短信数加 1 条
                //账户余额消费0.1元，（1MB流量）
                card.setMoney(card.getMoney() - 0.1);
                card.setConsumAmout(card.getConsumAmout() + 0.1);  //当月消费金额 + 0.1
            } else {
                temp = i; //记录使用流量多少MB
                throw new Exception("短信已经使用" + i + "条，您的余额不足，请充值后再使用！");
            }
        }
        return temp;
    }

    @Override
    public int Call(int smscount, MobileCard card) throws Exception {
        int temp =smscount;
        for (int i = 0; i <smscount ; i++) {
            if(this.talktime - card.getRealTakTime() >= 1){
                //第一种情况：套餐剩余通话时长可以支持1分钟通话
                card.setRealTakTime(card.getRealTakTime() + 1);
            }else if(card.getMoney() >= 0.2){
                //第二种情况：套餐通话时长已用完，账户余额可以支付1分钟通话，使用账户余额支付
                card.setRealTakTime(card.getRealTakTime() + 1); //实际使用通话时长1分钟
                //账户余额消费0.2元（1分钟 额外通话）
                card.setMoney(card.getMoney() - 0.2);
                card.setConsumAmout(card.getConsumAmout() + 0.2);  //当月消费金额 + 0.2
            }else{
                temp = i; //记录实际通话分钟数
                throw new Exception("本次已经通话" + i + "分钟，您的余额不足，请充值后再使用！");

            }
        }
        return temp;
    }
}
