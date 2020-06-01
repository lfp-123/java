package com.seecen.ssm.service.impl;

import com.seecen.ssm.mapper.OAdminMapper;
import com.seecen.ssm.pojo.OAdmin;
import com.seecen.ssm.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author ${林锋鹏}
 * @Title: AdminServiceImpl
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/17 12:42
 */
@Service

public class AdminServiceImpl implements AdminService {
    @Autowired
    OAdminMapper am;


    public OAdmin login(OAdmin admin) {
        return am.queryAccountPassword(admin);
    }


    public void add(OAdmin admin) {
        am.insert(admin);
    }


}
