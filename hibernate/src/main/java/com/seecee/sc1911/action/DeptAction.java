package com.seecee.sc1911.action;

import com.seecee.sc1911.pojo.HDept;
import com.seecee.sc1911.service.DeptService;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: DeptAction
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/29 22:28
 */
public class DeptAction{
    DeptService ds=new DeptService();
    List<HDept> listDept;

    public String showDept(){
        listDept=ds.showDept();
        return "success";
    }

    public List<HDept> getListDept() {
        return listDept;
    }

    public void setListDept(List<HDept> listDept) {
        this.listDept = listDept;
    }
}
