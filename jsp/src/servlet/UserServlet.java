package servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ${林锋鹏}
 * @Title: ServletTest
 * @ProjectName Java
 * @Description: 实现方式
 * 1、实现servlet接口 并且实现里面的方法（init destory service） 通过web.xml 配置servlet
 *  init() 初始化 servlet 在实例化之后 ，当第一次发送才会调用一次，并且实例化之后调用，单例模式 （枚举）
 *  destory() 销毁servlet web容器（web项目编译的空间-tomcat 关闭才会调用
 *  service 处理请求和响应
 *  <servlet >
 *      <servlet-name>任意名称</servlet-name>
 *      <servlet-class>指定servlet全类名</servlet-class>
 *      </servlet>
 *      <servlet-mapping>
 *          <servlet-name>对应的任意名称</servlet-name>
            <url-pattern>/名称</url-pattern>
  *      </servlet-mapping>
 * @date 2019/12/11 9:40
 */
public class UserServlet  implements Servlet {
    String charset;
    public UserServlet(){
  //      System.out.println("实例化方法，第一次发送请求执行，在初始化之前执行！");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
     //   System.out.println("初始化第一次调用！");
         charset = servletConfig.getInitParameter("charset");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

     //   System.out.println("处理用户请求跟响应，没法送一次请求会调用一次！");
        servletResponse.setContentType(charset);
        PrintWriter out = servletResponse.getWriter();  //获取字符输出流
        out.print("<html>");
        out.print("<body>");
        out.print("<table>");
        out.print("<tr>");
        out.print("<th>用户</th><th>密码</th><th>编号</th>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<th>admin</th><th>123</th><th>1</th>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<th>asd</th><th>123</th><th>2</th>");
        out.print("</tr>");
        out.print("</table>");
        out.print("</body>");
        out.print("</html>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println(
                "销毁servlet！！"
        );
    }
}
