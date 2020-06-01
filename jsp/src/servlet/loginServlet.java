package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: loginServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/11 14:27
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String un = req.getParameter("un");
        String pw = req.getParameter("pw");
        if(un.equals("admin")&&pw.equals("1")){
            resp.sendRedirect("/jsp/show");
        }else {
            resp.sendRedirect("/jsp/day1211/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req,resp);
            //两个请求之间相互调用这样无论发送post 、get 请求
            //都可以不被处理
    }
}
