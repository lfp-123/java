package servlet;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import dao.emailUserDao;
import entity.emailUser;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: servlet_login
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/11 19:54
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    HttpSession session ;
    UserService us = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        req.setCharacterEncoding("UTF-8");
        if(type.equals("login")) {
            login(req,resp);
        }else if(type.equals("reg")){
            register(req,resp);
        }else if(type.equals("queryAll")){
            queryAll(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    public void register(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        // 做什么 获取数据 判断跳转
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //依赖于service 做业务(功能执行是否成功失败,可能包含很多子功能,转换数据类型等 做事务 做异常)

        boolean register = us.register(username, password, email);
        if(register) {
            resp.sendRedirect("/email/index.jsp");
        }else{
            resp.sendRedirect("/email/register.jsp");
        }
    }
    public void login(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //进入服务层,servlet只进行接受数据返回数据进行处理
        emailUser eu = us.login(username, password);
        if (eu!= null){
            session = req.getSession();
            session.setAttribute("eu", eu);
            resp.sendRedirect("/email/main");
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<emailUser> emailUsers = us.queryAll();
        req.setAttribute("emailUsers",emailUsers);
        req.getRequestDispatcher("newMsg.jsp").forward(req,resp);
    }
}
