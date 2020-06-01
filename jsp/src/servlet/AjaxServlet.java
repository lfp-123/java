package servlet;

import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ${林锋鹏}
 * @Title: AjaxServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 16:19
 */
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //不能跳转，必须使用流写回来 实现异步
        String un = req.getParameter("un");
        //通过获取的数据 和数据库进行交互
        PersonService ps = new PersonService();
        String mess="";
        if (ps.checkUser(un)) {
            mess="用户名已存在！";
        }else {
            mess="用户名可以使用！";
        }
        PrintWriter writer = resp.getWriter();
        writer.print(mess); //只能识别字符串

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
