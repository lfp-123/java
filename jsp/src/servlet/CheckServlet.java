package servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author ${林锋鹏}
 * @Title: CheckServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/19 9:57
 */
@WebServlet("/checkU")
public class CheckServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String un = req.getParameter("un");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("111");
        strings.add("122");
        strings.add("555");
        Gson g = new Gson();
        g.toJson(strings);

        PrintWriter out = resp.getWriter();
        if(true){
            out.print(strings);
        }
    }
}
