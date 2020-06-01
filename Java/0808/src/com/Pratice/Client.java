package com.Pratice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: Client
 * @ProjectName Java
 * @date 2019/8/9 16:06
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 7894);
        File file = new File("H:1.png");
        //创建一缓冲读硬盘
        BufferedInputStream bn = new BufferedInputStream(new FileInputStream(file));
        //创建一个缓冲往外发
        BufferedOutputStream bp = new BufferedOutputStream(socket.getOutputStream());
        byte[] b=new byte[1024*512];
        int len;
        while ((len=bn.read(b))!=-1){
            bp.write(b);
            bp.flush();
        }
        System.out.println("文件发送完毕");
        bn.close();
        socket.close();
        bp.close();




    }
}
