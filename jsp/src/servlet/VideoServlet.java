package servlet;

import com.google.gson.Gson;
import entity.comments;
import service.PersonService;
import service.commentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: VidepServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 18:50
 */
@WebServlet("/video")
public class VideoServlet extends HttpServlet {
    commentService cs = new commentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String type = req.getParameter("type");
        if(type==null){
            showComments(req,resp);
        }else if(type.equals("comment")){
            comment(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req,resp);
    }

    private void showComments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<comments> list = cs.showComments();
        req.setAttribute("list",list);
        req.getRequestDispatcher("video.jsp").forward(req,resp);

    }
    private void comment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String un = req.getParameter("name");
        String comment = req.getParameter("content");
        List<comments> list = cs.comment(un, comment);

        //不能存作用域 因为只有刷新了页面 才能取出作用域的值
        //但是如果刷新页面了 就不是异步请求了
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        String s = gson.toJson(list);
        out.print(s);


    }
}
