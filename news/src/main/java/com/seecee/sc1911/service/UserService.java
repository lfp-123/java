package com.seecee.sc1911.service;

import com.seecee.sc1911.dao.UserDao;
import com.seecee.sc1911.pojo.Users;

/**
 * @author ${林锋鹏}
 * @Title: UserService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/20 16:18
 */
public class UserService {
    UserDao ud = new UserDao();
    public int insertUser(String un,String pw){

       return ud.insertUser(un,pw);
    }
    public Users qureyAll(String name, String pw){
       return ud.queryAll(name, pw);
    }
}
