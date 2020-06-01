package servlet;

import entity.EUser;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: showServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 14:23
 */
@WebServlet("/shows")
public class showServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService ps = new PersonService();
        List<EUser> eUsers = ps.queryAllEuer();
        req.setAttribute("eu",eUsers);
        req.getRequestDispatcher("day1216/loginshow.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
