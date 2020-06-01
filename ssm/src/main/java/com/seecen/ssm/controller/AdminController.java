package com.seecen.ssm.controller;

import com.seecen.ssm.pojo.OAdmin;
import com.seecen.ssm.service.AdminService;
import com.seecen.ssm.util.CodeUtil;
import com.seecen.ssm.util.MD5;
import com.seecen.ssm.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: AdminController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/17 12:36
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService as;
    @RequestMapping("/login")
    public String login(OAdmin admin, HttpSession session, String code, Model m){
        //先取出生成的验证码，再取出输入的
        String  randomCode = (String)session.getAttribute("randomCode");
        System.out.println("系统生成："+randomCode);
        System.out.println("用户输入："+code);
        if(!code.equals(randomCode)){
           session.setAttribute("error","验证码输入错误！");
        }else {
            //解密
            admin.setPassword(MD5.MD5Code(admin.getPassword()));
            OAdmin a = as.login(admin);
            if (a != null) {
                session.setAttribute("admin", a);
                return "redirect:/admin/home.jsp";
            } else {
               session.setAttribute("error","账号或密码错误！");
            }
        }
        return "redirect:/admin/login.jsp";
    }
    @RequestMapping("/add")
    public String add(OAdmin admin, MultipartFile imgfile, HttpServletRequest req){
        //先实现文件上传
        String newName = UploadUtil.upload(imgfile, req);
        admin.setHeadPic(newName);
        //密码通过MD5加密保存 但是不安全 网上有破解 所以加密n次
         admin.setPassword(MD5.MD5Code(admin.getPassword()));
         as.add(admin);
        System.out.println(admin);
        return "redirect:/admin/home.jsp";
    }
    @RequestMapping("/getCode")
    public void getCode(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CodeUtil.processRequest(req,resp);
    }
    @RequestMapping("/test")
    public void test(){
        System.out.println("1111111");
    }
}
