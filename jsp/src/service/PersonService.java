package service;


import dao.UserDao;
import entity.EUser;

import java.util.List;


/**
 * @author ${林锋鹏}
 * @Title: PersonService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 11:18
 */
public class PersonService {
   EUser euser =  new EUser();
    UserDao ud =  new UserDao();
    public boolean register(String username,String password,String path){
        int register = ud.register(username, password, path);
        if(register>0){
            return  true;
        }else {
            return false;
        }
    }
    public EUser login(String un,String pw) {
        EUser eUser = ud.login(un, pw);
        return eUser;

    }
    public List<EUser> queryAllEuer(){
        List<EUser> eUsers = ud.queryListEUser();
        return  eUsers;
    }
    public boolean checkUser(String name){
       return ud.checkUser(name);
    }

}
