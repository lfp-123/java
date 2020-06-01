package servlet;

import dao.mailboxDao;
import entity.emailUser;
import entity.mailbox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: MainServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/11 20:06
 */
@WebServlet(
        displayName = "main", // servlet-name
        urlPatterns = {"/main"}, // url - pattern
        loadOnStartup = 1, // loadOnStartup
        initParams = {@WebInitParam(name = "charset",value = "utf-8")} //init-param
)
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mailboxDao md = new mailboxDao();
        HttpSession session = req.getSession();
        emailUser eu =(emailUser)session.getAttribute("eu");
        List<mailbox> mailboxes = md.queryAllByToname(eu.getUsername());
        req.setAttribute("mailboxes",mailboxes);
        req.getRequestDispatcher("main.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
