package com.action;


import com.orm.TAdmin;
import com.service.AdminLoginService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: login_servlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/23 17:30
 */
@WebServlet("/loginAdmin")
public class login_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先将编码统一转化，避免接收到中文乱码
        req.setCharacterEncoding("utf-8");
        AdminLoginService ser = new AdminLoginService();
        String un = req.getParameter("userName");
        String pw = req.getParameter("userPw");
        System.out.println(un+pw);
        //获取登录名和密码进行数据库查询对象判断是否存在
        TAdmin admin = ser.login(un, pw);
        //如果查出该对象，则说明账号密码输入正确，存入session作用域
        System.out.println(admin);
        if(admin!=null){
            HttpSession session = req.getSession();
            session.setAttribute("userType", 0);
            session.setAttribute("admin",admin);
            resp.sendRedirect(  "/bs/admin/index.jsp");
        }else {
            //如果账号密码输入错误，则会跳转回原页面进行展示
            resp.sendRedirect("/bs/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        //两个请求之间相互调用这样无论发送post 、get 请求
        //都可以不被处理
    }

}
