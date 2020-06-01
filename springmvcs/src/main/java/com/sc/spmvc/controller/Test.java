package com.sc.spmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/2/7 17:11
 */
@Controller
@RequestMapping("/test")
public class Test {
    @RequestMapping("/testPath/{sid}")
    public String  testPath(@PathVariable(name = "sid")Integer id){
        System.out.println(id);
        return "success";
    }
}
