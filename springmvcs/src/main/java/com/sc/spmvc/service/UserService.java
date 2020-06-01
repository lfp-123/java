package com.sc.spmvc.service;

import com.sc.spmvc.pojo.SUser;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserService
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/8 11:52
 */
public interface UserService {
    public SUser login(SUser user);
    public int register(SUser user);
    public SUser checkUser(String username);
    public List<SUser> showUserList();
    public int delUser(SUser user);
}
