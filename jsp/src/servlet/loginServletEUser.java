package servlet;

import entity.EUser;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: loginServletEUser
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 11:48
 */
@WebServlet("/logins")
public class loginServletEUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String un = req.getParameter("un");
        String pw = req.getParameter("pw");
        PersonService ps= new PersonService();
        EUser u = ps.login(un, pw);
        if (u != null){
            //登陆成功存储用户信息
            req.getSession().setAttribute("u", u);
            resp.sendRedirect(req.getContextPath() + "/shows");
        } else {
            resp.sendRedirect(req.getContextPath() + "/day1216/login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

