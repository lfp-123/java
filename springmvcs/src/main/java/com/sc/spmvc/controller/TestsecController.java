package com.sc.spmvc.controller;

import com.sc.spmvc.pojo.SUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author ${林锋鹏}
 * @Title: TestsecController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/8 10:06
 */
@Controller
public class TestsecController {

    @RequestMapping("/aa")
    public ModelAndView aa(ModelAndView m2, SUser user){
        ModelAndView m = new ModelAndView();
        // m 存入模型（传递的数据）视图（指定的页面）存入model 相当于存入request作用域
        user.setUsername("张三");
        user.setPassword("1234465");
        m.addObject("u",user);
        m.setViewName("/show.jsp");
        return m;
    }
}
