package filter;


import entity.emailUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: CheckLoginFilter
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/17 15:21
 */
public class CheckLoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        emailUser eu = (emailUser) session.getAttribute("eu");
        String uri = req.getRequestURI();
        System.out.println(uri);
        String path = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println(path);
//        if(eu==null&&!(path.equals("index.jsp")&&path.equals("register.jsp"))){
//            req.getRequestDispatcher("/email/index.jsp").forward(req,resp);
//            return;
//           //代表让请求放行
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁！！");
    }
}
