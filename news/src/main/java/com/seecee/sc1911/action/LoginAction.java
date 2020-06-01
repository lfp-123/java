package com.seecee.sc1911.action;

import com.seecee.sc1911.pojo.NewsContent;
import com.seecee.sc1911.pojo.Users;
import com.seecee.sc1911.service.NewsContentService;
import com.seecee.sc1911.service.UserService;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: LoginAction
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/20 14:54
 */
public class LoginAction extends BaseAction {
    Users u;
    String name;
    String pw;
    UserService us = new UserService();
   NewsContentService nts= new NewsContentService();
    public String login() {
        System.out.println(name);
        System.out.println(pw);
        Users users = us.qureyAll(name,pw);
        List<NewsContent> list = nts.queyrAll();
        if (users!=null){
            req.getSession().setAttribute("user",users);
            req.getSession().setAttribute("list",list);
            return "login";
        }else {
            return "register";
        }
    }
    public String register(){
        int i = us.insertUser(name,pw);

        if(i>0) {
            return "login";
        }else {
            return "register";
        }
    }
    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
