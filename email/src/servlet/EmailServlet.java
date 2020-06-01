package servlet;

import dao.mailboxDao;
import entity.emailUser;
import entity.mailbox;
import service.EmailService;

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
 * @Title: emailServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/12 15:40
 */
@WebServlet("/email")
public class EmailServlet extends HttpServlet {
    HttpSession session;
    EmailService emailservice = new EmailService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        session = req.getSession();
        String type = req.getParameter("type");
        if(type.equals("add")){
            addEmail(req,resp);
        }else if(type.equals("readfrom")){
            queryFromEmail(req,resp);
        }else if(type.equals("del")){
            delete(req,resp);
        }else if(type.equals("delall")) {
            delAll(req, resp);
        }else if(type.equals("yifa")){
            queryYifaByname(req,resp);
        }

    }

    private void queryYifaByname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        emailUser eu =(emailUser)session.getAttribute("eu");
        List<mailbox> mailboxes = emailservice.queryFromEmail(eu.getUsername());
        req.setAttribute("mailboxes",mailboxes);
        req.getRequestDispatcher("yifaMsg.jsp").forward(req,resp);
    }

    private void delAll(HttpServletRequest req, HttpServletResponse resp) {
        String[] cks = req.getParameterValues("ck");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    public void addEmail(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        emailUser eu = (emailUser)session.getAttribute("eu");
        String username = eu.getUsername();
        String toname = req.getParameter("toname");
        String content = req.getParameter("content");
        String title = req.getParameter("title");
        boolean b = emailservice.addEmail(username, toname, title, content);
        PrintWriter writer = resp.getWriter();
        if(b){
            writer.print("<script>alert('邮件发送成功!');location.href='/email/main.jsp';</script>");
        }else{
            writer.print("<script>alert('邮件发送失败!');location.href='/email/main.jsp';</script>");
        }
    }
    public void queryFromEmail(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        emailUser eu = (emailUser)session.getAttribute("eu");
        String username = eu.getUsername();
        List<mailbox> mailboxes =  emailservice.queryFromEmail(username);
        req.setAttribute("mailboxes",mailboxes);
        req.getRequestDispatcher("yifaMsg.jsp").forward(req,resp);

    }
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        boolean del = emailservice.del(id);
        PrintWriter writer = resp.getWriter();
        if(del){
            writer.print("<script>alert('邮件删除成功!');location.href='/email/main';</script>");
        }else{
            writer.print("<script>alert('邮件删除失败!');location.href='/email/main';</script>");
        }

    }

}
