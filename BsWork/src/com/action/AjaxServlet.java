package com.action;

import com.orm.Tstu;
import com.orm.Ttea;
import com.service.loginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ${林锋鹏}
 * @Title: Ajax
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/28 15:56
 */
@WebServlet("/ajax")
    public class AjaxServlet  extends HttpServlet{
    loginService service = new loginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("name");
        String pw = req.getParameter("pw");
        String type = req.getParameter("type");
        Integer realType = Integer.valueOf(type);
        HttpSession session = req.getSession();
        String result="yes";
        System.out.println(name+" "+pw);
        if(realType==1){
            Ttea tea = service.loginTtea(name, pw);
            if(tea==null){
                result="no";
            }else {
                session.setAttribute("userType", 1);
                session.setAttribute("tea", tea);
            }
        }
        if(realType==2){
            Tstu stu = service.loginTstu(name, pw);
            if(stu==null){
                result="no";
            }else {
                session.setAttribute("userType", 2);
                session.setAttribute("stu", stu);
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }


}
