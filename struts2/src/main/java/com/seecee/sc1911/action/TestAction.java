package com.seecee.sc1911.action;

import com.seecee.sc1911.pojo.User;

import java.util.Arrays;

/**
 * @author ${林锋鹏}
 * @Title: TestAction
 * @ProjectName Java
 * @Description: 本来默认需要实现一个接口，actionSuppot 实现里面excut()
 * 并且返回String 后来比较简单 什么都不需要继承只选返回值 能够实现什么：替换servlet 处理用户请求和响应
 * 需要在strut配置 请求和方法之间的映射
 * @date 2019/12/20 9:49
 */
public class TestAction {
    /*
        struts2数据流入和流出
        数据流入：1 属性驱动：在action类中定于对应的属性，并且实现对应get/set方法
            要求传递的属性名，必须根表单组件的name值一致
            a.基本数据类型的传递：默认按照上面的方式
            b.对象类型可以直接定义对象的属性，提供get/set 前台传递的name值 必须==对象.对象属性名
            struts2加载action类时，会自动将里面的所有成员进行实例化 这些所有功能都是struts2拦截器实现

            2 对象驱动：
             1,实现接口 ModelDriver<泛型>接口
             2,定义对象的属性，该对象必须实例化，
             3,实现一个方法 getModel();
             好处：前台表单组件可以写对象属性名 name
             坏处：处理多个对象处理不方便
        数据流出：
            xml json 作用域 地址栏传值
            本身会将action类中的所有成员变量存入request作用域
            但是得提供get/set 方法


     */
String un;
String pw;
String[] ck;
User u;

    public String test(){
        System.out.println("测试Action方法");
        System.out.println("用户："+un);
        System.out.println("密码："+pw);
        System.out.println("爱好："+ Arrays.toString(ck));
        System.out.println("用户对象"+u.toString());
        //如何实现页面跳转 ，struts是通过action返回值来定义的
        //方法的返回值 和配置文件 result 标签进行映射 （就会映射对应的页面）
        return "hh";
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }


    public String[] getCk() {
        return ck;
    }

    public void setCk(String[] ck) {
        this.ck = ck;
    }




    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }



}
