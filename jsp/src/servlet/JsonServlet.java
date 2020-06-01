package servlet;

import com.google.gson.Gson;
import entity.EUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: JsonServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/17 9:47
 */
@WebServlet("/json")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("异步请求");
        EUser eUser = new EUser();
        Gson g = new Gson();
        String s = g.toJson(eUser);
        System.out.println(s);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
