package servlet;

import service.PersonService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ${林锋鹏}
 * @Title: EUserServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 9:45
 */
@WebServlet(urlPatterns = "/register",
            loadOnStartup = 1)
@MultipartConfig //标注servlet支持上传文件
public class EUserServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        //定义一个初始化，让其服务器启动运行，获取项目前缀，存入作用域 application级别
        String prefix = config.getServletContext().getContextPath();
        config.getServletContext().setAttribute("prefix",prefix);
        System.out.println("项目前缀："+prefix);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取文件上传的内容的对象  该对象可以获取上传
        Part part = req.getPart("myFile");
        String un = req.getParameter("un");
        String pw = req.getParameter("pw");
        //数据保存？在服务器项目路径中提供一个包unload ，这个包用来上传文件
        //数据库保存upload下的文件路径，下次想获取 通过数据库查询路径
        //2，通过part对象获取文件名
        String fileName = part.getSubmittedFileName();
        //3 获取项目真实路径
        String realPath = req.getServletContext().getRealPath("/upload");//真实路径加路径
        System.out.println("项目运行真实路径："+realPath);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //项目路径固定+ 文件名应该随机生成+文件后缀名
        //文件名随机生成的方式：随机数、时间戳、UUID
        String name = UUID.randomUUID().toString();
        //随机生成一个32位永不重复的字符串
        System.out.println("文件名"+name);
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件后缀："+suffix);
        String path = realPath+File.separator+name+suffix;
        System.out.println("文件上传路径："+path);
        //4将上传文件保存到指定路径path
        part.write(path);
        //5 持久化 （数据库操作）
        PersonService ps = new PersonService();
        boolean register = ps.register(un, pw, name+suffix);
        System.out.println(un+" "+pw+" "+path);
        if(register){
            resp.sendRedirect(req.getContextPath()+"/day1216/login.jsp");
        }else {
            resp.sendRedirect(req.getContextPath()+"/day1216/register.jsp");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
