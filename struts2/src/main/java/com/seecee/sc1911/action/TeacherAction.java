package com.seecee.sc1911.action;

/**
 * @author ${林锋鹏}
 * @Title: TeacherAction
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/23 14:39
 */
public class TeacherAction {
    public String show(){
        System.out.println("进入show页面！！");
        return "success";
    }
    public String add(){
        System.out.println("进入add页面！！");
        return "success";
    }
    public String test(){
        System.out.println("请求地址有误！");
    return "error";
    }
}
