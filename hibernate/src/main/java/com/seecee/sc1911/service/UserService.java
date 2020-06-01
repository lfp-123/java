package com.seecee.sc1911.service;

import com.seecee.sc1911.dao.UserDao;
import com.seecee.sc1911.pojo.HDept;
import com.seecee.sc1911.pojo.HUser;
import com.seecee.sc1911.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/25 14:36
 */
public class UserService {
UserDao userDao=   new UserDao();
    public HUser login(HUser u){
        HUser user = userDao.login(u);
        HibernateUtil.close();
        return  user;
    }
    public List<HUser> queryUser(int pageIndex ,int pageCount){
        List<HUser> hUsers = userDao.queryUser(pageIndex,pageCount);
        return hUsers;
    }
    public boolean addUser(HUser u){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        try {
            userDao.addUser(u);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return  false;
        }finally {
            HibernateUtil.close();
        }
        return true;
    }
    public boolean delUser(long  id){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        try {
            userDao.delUser(id);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return  false;
        }finally {
            HibernateUtil.close();
        }
        return true;
    }
    public boolean delAllUser(String[] strings){
        for (int i = 0; i <strings.length ; i++) {
            long l = Long.parseLong(strings[i]);
            delUser(l);
        }
        return true;
    }
    public boolean upUser(HUser u){
        Session sessin = HibernateUtil.getSessin();
        Transaction tx = sessin.beginTransaction();
        try {
            userDao.upUser(u);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return  false;
        }finally {
            HibernateUtil.close();
        }
        return true;
    }
    public HUser showOneUser(long id){
       HUser us = userDao.showOneUser(id);
       return  us;
    }
    public List<HDept> showDept(){
        return userDao.showDept();
    }
}
