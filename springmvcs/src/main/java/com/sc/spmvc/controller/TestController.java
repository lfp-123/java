package com.sc.spmvc.controller;

import com.sc.spmvc.pojo.SUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author ${林锋鹏}
 * @Title: TestController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/7 14:44
 */
@Controller

public class TestController {

    @RequestMapping("/hehe")
    public String first(String name, String password, ServletRequest req, ServletConfig cig, ServletContext sct, HttpSession session){


        System.out.println("springmvc");
        System.out.println("账号："+name);
        System.out.println("密码："+password);
        return "/show.jsp";
    }
    @RequestMapping("/register")
    public String register(SUser user,String[] ck,Model m,HttpServletRequest req){
        System.out.println(Arrays.toString(ck));
        //map作为request作用域存值
        System.out.println(user);
        System.out.println(Arrays.toString(user.getCk()));
        m.addAttribute("u1",user);
        req.setAttribute("u",user);
        return "/show.jsp";
    }
}
