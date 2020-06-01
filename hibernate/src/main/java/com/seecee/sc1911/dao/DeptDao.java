package com.seecee.sc1911.dao;

import com.seecee.sc1911.pojo.HDept;
import com.seecee.sc1911.until.HibernateUtil;


import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: DeptDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/29 22:36
 */
public class DeptDao {
    public List<HDept> showDept(){
        return  HibernateUtil.getSessin().createQuery("from HDept ").list();
    }
}
