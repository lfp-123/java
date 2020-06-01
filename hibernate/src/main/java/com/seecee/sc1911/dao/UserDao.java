package com.seecee.sc1911.dao;
import com.seecee.sc1911.pojo.HDept;
import com.seecee.sc1911.pojo.HUser;

import com.seecee.sc1911.pojo.HUserinfo;
import com.seecee.sc1911.until.HibernateUtil;

import org.hibernate.Session;

import org.hibernate.query.Query;


import java.util.Date;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: UserDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/25 14:37
 */
public class UserDao {
   DeptDao deptDao =  new DeptDao();

    //登陆判断
    public HUser login(HUser u){
        Session session = HibernateUtil.getSessin();
        Query q = session.createQuery("from HUser where username =:username " +
                "and password=:password");
        q.setProperties(u); //自动传参 命名参数的名称 必须是对象的属性名 。

        return (HUser)q.uniqueResult();
    }
    //分页查询用户信息
    public List<HUser> queryUser(int pageIndex,int pageCount){
        Query q = HibernateUtil.getSessin().createQuery("from HUser");
        q.setFirstResult((pageIndex-1)*pageCount);
        q.setMaxResults(pageCount);
        return    q.list();
    }
    //注册用户
    public void addUser(HUser u){
        u.setCreatetime(new Date());
        u.getInfo().setUser(u);
        HibernateUtil.getSessin().save(u);
    }
    //删除用户
    public void delUser(long  u){
        Session sessin = HibernateUtil.getSessin();
        HUser hUser = sessin.get(HUser.class, u);
        sessin.delete(hUser);

    }
    //修改。。。。。。。
    public void upUser(HUser u){
        Session sessin = HibernateUtil.getSessin();
        HUser hUser = sessin.get(HUser.class, u.getId());
        hUser.setDept(u.getDept());
        hUser.setInfo(u.getInfo());
        hUser.setPassword(u.getPassword());
        hUser.setUsername(u.getUsername());
        hUser.setCreatetime(new Date());

        u.getInfo().setUser(u);



    }
    public HUser showOneUser(long id){
        
        return  HibernateUtil.getSessin().get(HUser.class,id);
    }
    public List<HDept> showDept(){
        return  HibernateUtil.getSessin().createQuery("from HDept ").list();
    }


}

