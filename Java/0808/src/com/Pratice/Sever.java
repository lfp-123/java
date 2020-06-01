package com.Pratice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: Sever
 * @ProjectName Java
 * @date 2019/8/9 15:52
 */
public class Sever {
    public static void main(String[] args) throws  Exception{
        System.out.println("服务器已启动...");
        ServerSocket serverSocket = new ServerSocket(7894);
        Socket accept = serverSocket.accept();
        File file = new File("D:a.png");
        //输入硬盘
        BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(file));
        //收到的文件流
        BufferedInputStream bf = new BufferedInputStream(accept.getInputStream());
        byte[] b = new byte[1024*512];
        int len;
        while ((len =bf.read(b))!=-1){
                bo.write(b);
        }
        //关闭流
        bo.close();
        bf.close();
        accept.close();
        System.out.println("文件接受完毕！");







    }
}
