package com.sc.spring.controller;


import com.sc.spring.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author ${林锋鹏}
 * @Title: UserController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/12 14:18
 */
@Controller("uc")
public class UserController {
    @Resource
    UserService us2;

    public void setUs2(UserService us2) {
        this.us2 = us2;
    }

    public String login(){
        System.out.println("进入登陆控制台");
        us2.login();
        return "/index.jsp";
    }
}
