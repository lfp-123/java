package com.sc.spring.service.impl;

import com.sc.spring.mapper.UserMapper;
import com.sc.spring.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ${林锋鹏}
 * @Title: UserServiceImpl2
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/12 17:07
 */
@Service("us2")
public class UserServiceImpl2 implements UserService {
    @Resource
    UserMapper mapper;
    @Override
    public void login() {
        mapper.login();
        System.out.println("使用第二种service方法！");

    }


}
