package com.communication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @Author bigpig
 * @Date 2019/8/9 9:16
 * @Description
 */
public class FileSend {
    public static void main(String[] args) {
        // 要发送的文件
        File file = new File("E:\\windows安装包\\java\\apache-tomcat-8.5.20-windows-x64.zip");
        String hz = file.getName().substring(file.getName().lastIndexOf("."));
        try (
                Socket socket=new Socket("localhost",9527);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file), 1024 * 1024 << 1);
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream(), 1024 * 1024 << 1);
        ) {
            // 先发送一个文件名过去
            bos.write(hz.getBytes());
            bos.flush(); // 一定要单独冲刷一下
            int len = 0;
            byte[] datas = new byte[1024 * 512];
            while ((len = bis.read(datas)) != -1) {
                bos.write(datas, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
