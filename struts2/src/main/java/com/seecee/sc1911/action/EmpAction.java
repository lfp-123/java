package com.seecee.sc1911.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * @author ${林锋鹏}
 * @Title: EmpAction
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/20 9:50
 */

public class EmpAction extends BaseAction {

    public String emp(){
        /*获取session req.getSession()
          利用工具类    ActionContext 获取session ，封装类封装好
         */
        Map<String, Object> s = ActionContext.getContext().getSession();
        s.put("name","张三");
        /*
        第二种获取
         */
        HttpServletRequest req = ServletActionContext.getRequest();
        HttpServletResponse resp = ServletActionContext.getResponse();
        HttpSession session = ServletActionContext.getRequest().getSession();
        ServletContext application = ServletActionContext.getServletContext();
        PageContext page = ServletActionContext.getPageContext();
        /*
        第三种
         */
        HttpSession sess = req.getSession();
        return "success";
    }
}
