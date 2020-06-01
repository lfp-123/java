package com.seecee.sc1911.action;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ${林锋鹏}
 * @Title: BaseAction
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/23 17:42
 */
public class BaseAction implements ServletRequestAware,ServletResponseAware{
    HttpServletRequest req;
    HttpServletResponse resp;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        req= httpServletRequest;

    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        resp= httpServletResponse;
    }


}
