package com.sc.spring.service.impl;

import com.sc.spring.mapper.UserMapper;
import com.sc.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ${林锋鹏}
 * @Title: UserServiceImpl
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/12 14:17
 */
@Service("us1")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;

    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void login() {
        System.out.println("调用login的service方法");
        mapper.login();
        //mapper

    }


}
