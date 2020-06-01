package com.sc.spmvc.controller;

import com.sc.spmvc.pojo.SUser;
import com.sc.spmvc.pojo.User;
import com.sc.spmvc.service.Impl.UserServiceImpl;
import com.sc.spmvc.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ${林锋鹏}
 * @Title: UserController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/8 10:34
 */
@Controller
@MultipartConfig //如果想实现上传，就要加这个注解
@RequestMapping("/user")
public class UserController {
    UserService userservice =  new UserServiceImpl();

    @RequestMapping("/login")
    public String login(SUser user, HttpSession session){
        SUser u = userservice.login(user);
        if(u!=null){
            session.setAttribute("u",u);
            return "redirect:showUser";
        }
        return  "forward:/login.jsp";
    }
    //注册功能，同时上传头像
    @RequestMapping("/register")
    public String register(SUser user, MultipartFile imageFile, HttpServletRequest req){
        //a.获取文件名
        String oldName = imageFile.getOriginalFilename();
        //获取后缀名
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        //随机产生文件名，产生文件名
        String newName = UUID.randomUUID().toString()+suffix;
        //将文件保存到指定目录
        String realPath = req.getServletContext().getRealPath("/upload");
        String path=  realPath+File.separator+newName;
        user.setHead(newName);
        File file = new File(path);
        if(!file.exists()){
            System.out.println("文件不存在！已创建新的目录！");
            file.mkdirs();
        }
        try {
            imageFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setHead(newName);
        int i = userservice.register(user);
        if(i>0){
            return "forward:/login.jsp";
        }
        return  "forward:/register.jsp";
    }
    //下载文件，不跳转页面
    @RequestMapping("/imageDownload")
    public ResponseEntity<byte[]> imageDownload(String fileName, HttpServletRequest req) throws IOException {

        fileName = URLEncoder.encode(fileName,"utf-8");
        //获取文件的真实目录
        String  path = req.getServletContext().getRealPath("/upload")+File.separator+fileName;
        //对文件路径进行URL编码 防止中文路径出现乱码

        //获取头部信息
        HttpHeaders headers = new HttpHeaders();
        //设置返回类型是流
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //指定以附件形式下载
        headers.setContentDispositionFormData("attachment",fileName);
        //以附件形式下载并传递文件名
        //读取FIle转化bytes数组
        byte[] bytes = FileUtils.readFileToByteArray(new File(path));
                                        //参数： 数组 头部信息 状态码
        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED);
    }


    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest req){
        List<SUser> list = userservice.showUserList();
        req.setAttribute("list",list);
        return  "user/showUser";
    }
    @RequestMapping("/addUser")
    public String addUser(){

        return "";
    }
    @RequestMapping("/delUser")
    public String deleteUser(SUser user){
        userservice.delUser(user);
        return "redirect:showUser";

    }
/*
验证异步请求处理
 */
    @RequestMapping(value = "/checkUser",produces="text/html;charset=utf-8")
    @ResponseBody //告诉springmvc返回值是响应结果
    public String checkUser(String username){
        SUser sUser = userservice.checkUser(username);
        if(sUser!=null){
            return "用户名已存在";
        }else {
            return "用户名可以使用";
        }

    }
    /*
        验证异步返回json
       想将什么类型转换json就定义什么类型返回值
       SpringMvc会自动转换为json(就是键值对的形式 对象：{属性名：属性})
       前提是导入jar包
 */
    @RequestMapping(value = "/checkUser2")
    @ResponseBody //告诉springmvc返回值是响应结果
    public List<SUser> checkUser2(String username){
        List<SUser> list = new ArrayList<>();
        list.add(new SUser(11,"张三","123","sss.txt",new Date()));
        list.add(new SUser(12,"张四","123","sss.txt",new Date()));
        list.add(new SUser(13,"张五","123","sss.txt",new Date()));
        list.add(new SUser(14,"张六","123","sss.txt",new Date()));
        list.add(new SUser(15,"张七","123","sss.txt",new Date()));
        return list;
    }
    @RequestMapping("/loginDIY")
    public String saveUser(User user){
        System.out.println("执行");
          System.out.println(user);
        return  "success";
    }


}
