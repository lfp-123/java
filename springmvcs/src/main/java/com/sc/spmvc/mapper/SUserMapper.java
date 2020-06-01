package com.sc.spmvc.mapper;

import com.sc.spmvc.pojo.SUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: SUserMapper
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/8 11:53
 */
public interface SUserMapper {

    //登陆
     SUser selectByUsernamePassword(SUser user);
    //注册
     int insertUser(@Param(value = "u") SUser u);
     //异步查询
    SUser checkUser(String username);
    //展示所有信息
    List<SUser> showListUser();
    //删除
    int delUSer(@Param(value = "u") SUser u);
}
