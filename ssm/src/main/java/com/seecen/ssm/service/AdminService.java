package com.seecen.ssm.service;

import com.seecen.ssm.pojo.OAdmin;

/**
 * @author ${林锋鹏}
 * @Title: AdminService
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/17 12:41
 */
public interface AdminService {
   public  OAdmin login(OAdmin admin);
   public void add(OAdmin admin);

}
