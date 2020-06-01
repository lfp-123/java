package service;

import dao.emailUserDao;
import entity.emailUser;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/12 11:40
 */
public class UserService {
    emailUserDao emailUserDao = new emailUserDao();
    public boolean register(String username,String password,String email){
        //依赖于dao层 (对数据增删改查)
        int i = emailUserDao.queryInset(username, password, email);
        if(i>0){
            return  true;
        }else {
            return  false;
        }
    }
    public emailUser login(String username,String password){
        //依赖于dao层
       return  emailUserDao.queryByUsernamePassword(username,password);
    }
    public List<emailUser> queryAll(){
        return  emailUserDao.queryAll();
    }
}

