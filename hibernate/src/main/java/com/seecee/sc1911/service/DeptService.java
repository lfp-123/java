package com.seecee.sc1911.service;

import com.seecee.sc1911.dao.DeptDao;
import com.seecee.sc1911.pojo.HDept;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: DeptService
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/29 22:35
 */
public class DeptService {
    DeptDao dp =   new DeptDao();
    public List<HDept> showDept(){
       return dp.showDept();
    }
}
