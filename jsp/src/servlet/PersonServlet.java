package servlet;

import dao.PersonDao;
import entity.Person;
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
 * @Title: PersonServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/11 11:09
 */
//@WebServlet("/show")
    @WebServlet(
            displayName = "person", // servlet-name
            urlPatterns = {"/show"}, // url - pattern
            loadOnStartup = 1, // loadOnStartup
            initParams = {@WebInitParam(name = "charset",value = "utf-8")} //init-param

    )
public class PersonServlet extends HttpServlet {
    @Override //处理get请求
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String charset = getInitParameter("charset");
        //查询数据的请求
        //获取前台的当前页数和每页条数
        HttpSession session = req.getSession();
        String pageIndex = req.getParameter("pageIndex");
        String pageCount =req.getParameter("pageCount");
        if(pageIndex==null){
            pageIndex="1";
        }
        if (pageCount==null){
             pageCount = (String) session.getAttribute("pageCount");
           if(pageCount==null){ pageCount = "5"; }
        }else{
            session.setAttribute("pageCount",pageCount);
        }
        int pageCounts = Integer.parseInt(pageCount);
        int pageIndexs = Integer.parseInt(pageIndex);
        PersonDao personDao = new PersonDao();
        /*
            查询总数据条数
         */
        int i = personDao.queryCount();
        int totalCount = i%pageCounts==0?i/pageCounts:i/pageCounts+1;
    //    req.setAttribute("pageCount",pageCount); 因为第一次request结束之后 默认值不存在了 于是改为session
        req.setAttribute("totalPage",totalCount);

        List<Person> ps = personDao.queryAll(pageIndexs,pageCounts);
        req.setAttribute("ps",ps);

        req.setAttribute("pageIndex",pageIndex);
        req.getRequestDispatcher("day1211/showPerson.jsp").forward(req,resp);
    }

    @Override //处理post请求
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
