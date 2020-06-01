package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * @author ${林锋鹏}
 * @Title: downServlet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/16 15:02
 */
@WebServlet("/download")
public class DownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //下载功能：提供路径 需要设置文件类型 和响应类型 通过流将数据返回浏览器
            //1、转码  防止传来的文件名带中文
        String fn = req.getParameter("fileName"); //获取文件名
        String path = req.getServletContext().getRealPath("/upload/" + fn);//返回项目的真实路径 war包地址 /接着获取文件的路径
        String mimeType = req.getServletContext().getMimeType(path);//获取文件类型
        resp.setContentType(mimeType); //设置响应的文件类型
        resp.setHeader("content-disposition","attachment;fileName="+fn);//设置响应的头部信息，指定为附件的形式下载，并且指定的文件名
        ServletOutputStream os = resp.getOutputStream(); //通过输入流先读取
        FileInputStream fis = new FileInputStream(path); //通过输出流输出
        byte[] bytes = new byte[1024];
        int leng =0;
        while ((leng=fis.read(bytes))!=-1){
            os.write(bytes,0,leng);
        }
        fis.close();
        os.close();



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     doGet(req,resp);
    }
}
