package com.seecen.dd.entity;

import java.io.Serializable;

/**
 * @author ${林锋鹏}
 * @Title: 移动卡
 * @ProjectName Java
 * @date 2019/8/15 16:23
 */
public class MobileCard implements Serializable {

    private String cardNumber;
    // 用户名
    private String userName;
    //密码
    private String passWord;
    //所属套餐
    private ServicePackage serPackage;
    //当月消费金额
    private double consumAmout;
    //账户余额
    private double money;
    //当月实际通话时长
    private int realTakTime;
    //当月实际发送短信条数
    private int realSMSCount;
    //当月实际上网流量
    private int realFlow;
    // 短信
    private  String message;
    // 用户信箱
    private  int mailbox;
    private  int systemmailbox;

    public int getSystemmailbox() {
        return systemmailbox;
    }

    public void setSystemmailbox(int systemmailbox) {
        this.systemmailbox = systemmailbox;
    }

    public int getMailbox() {
        return mailbox;
    }

    public void setMailbox(int mailbox) {
        this.mailbox = mailbox;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MobileCard(){}
    /**
     * 有参构造
     * @param cardNumber  卡号
     * @param userName	  名字
     * @param passWord	密码
     * @param serPackage	所属套餐
     * @param consumAmout	当月消费金额
     */
    public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage, double money){
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.money = money;
    }
    /**
     *  初始化用户信息
     * @param cardNumber  卡号
     * @param userName	  名字
     * @param passWord	密码
     * @param serPackage	所属套餐
     * @param consumAmout	当月消费金额
     * @param money 	账户余额
     * @param realTakTime	当月实际通话时长
     * @param realSMSCount	当月实际发送短信条数
     * @param realFlow	当月实际上网流量
     */
    public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage,
                      double consumAmout, double money, int realTakTime, int realSMSCount, int realFlow) {

        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.consumAmout = consumAmout;
        this.money = money;
        this.realTakTime = realTakTime;
        this.realSMSCount = realSMSCount;
        this.realFlow = realFlow;
    }
    /**
     * 卡号
     * @return
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * 卡号
     * @return
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    /**
     * 用户名
     * @return
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 用户名
     * @return
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 密码
     * @return
     */
    public String getPassWord() {
        return passWord;
    }
    /**
     * 密码
     * @return
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    /**
     * 所属套餐
     * @return
     */
    public ServicePackage getSerPackage() {
        return serPackage;
    }
    /**
     * 所属套餐
     * @return
     */
    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }
    /**
     * 当月消费金额
     * @return
     */
    public double getConsumAmout() {
        return consumAmout;
    }
    /**
     * 当月消费金额
     * @return
     */
    public void setConsumAmout(double consumAmout) {
        this.consumAmout = consumAmout;
    }
    /**
     * 账户余额
     * @return
     */
    public double getMoney() {
        return money;
    }
    /**
     * 账户余额
     * @return
     */
    public void setMoney(double money) {
        this.money = money;
    }
    /**
     * 当月实际通话时长
     * @return
     */
    public int getRealTakTime() {
        return realTakTime;
    }
    /**
     * 当月实际通话时长
     * @return
     */
    public void setRealTakTime(int realTakTime) {
        this.realTakTime = realTakTime;
    }
    /**
     * 当月实际发送短信条数
     * @return
     */
    public int getRealSMSCount() {
        return realSMSCount;
    }
    /**
     * 当月实际发送短信条数
     * @return
     */
    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }
    /**
     * 当月实际上网流量
     * @return
     */
    public int getRealFlow() {
        return realFlow;
    }
    /**
     * 当月实际上网流量
     * @param realFlow 上网流量
     */
    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }
    /**
     * 嗖嗖移动卡信息
     */
    public void showMeg() {
        System.out.println("卡号:"+this.getCardNumber()+"用户名:"+this.getUserName()+"当前余额:"+this.getMoney()+"元");
        this.serPackage.showInfo();
    }


}



