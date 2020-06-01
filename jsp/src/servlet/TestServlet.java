package servlet;

import dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author asus
 * @Date 2019/12/10 15:57
 * @Description
 * 实现servlet接口  实现里面5个方法  配置web.xml文件
 * servlet默认单例模式
 */

public class TestServlet implements Servlet {
    private String charSet;
    /*
    初始化方法：可以对servlet进行数据的初始化操作
    servletConfig  servlet配置对象，可以获取servlet配置信息
    * */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
     //   System.out.println("初始化成功！");
        //调用初始化参数  给charSet赋值
     //   int a = 10 / 0;
        charSet = servletConfig.getInitParameter("charSet");
    }
@Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
    处理用户请求和响应的核心方法
    servletRequest    request   请求对象
    servletResponse   response  响应对象
    * */
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
       // System.out.println("开始处理请求--------------");
        request.setCharacterEncoding(charSet);
        String name = request.getParameter("un");
        String password = request.getParameter("pw");
        UserDao userDao = new UserDao();
        userDao.insertUser(name, password, "sysDBA", "0", 28);
        boolean b = true;
        if (b){
            ((HttpServletResponse)response).sendRedirect("/jsp/day1210/login.jsp");
        }else {
            ((HttpServletResponse)response).sendRedirect("/jsp/day1210/show.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return null;
    }

    /*
    销毁Servlet方法
    * */
    @Override
    public void destroy() {
        System.out.println("servlet开始销毁！！！");
    }
}
