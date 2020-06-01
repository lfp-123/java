package com.communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * @Author bigpig
 * @Date 2019/8/9 9:16
 * @Description
 */
public class FileServer {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9527)) {
            // 服务器要保存的位置
            String hz = null;
            File dest = new File("d:" + File.separator + "upload");
            if (!dest.exists()) dest.mkdir(); // 创建该目录
            System.err.println("服务器已启动,等待传输...");
            while (true) {
                File newFile = new File(dest, UUID.randomUUID().toString());
                try (
                        // 接收一个新的客户
                        Socket socket = server.accept();
                        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream(), 1024 * 1024 << 1);
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile),1024 * 1024 << 1);
                ) {
                    newFile.createNewFile();
                    System.err.println(socket.getRemoteSocketAddress() + "已连上...准备接收传输..");
                    // 获取客户传输过来的后缀名称
                    int len = 0;
                    byte[] datas = new byte[1024 * 512];
                    len = bis.read(datas);
                    hz = new String(datas, 0, len);
                    // 接收并写入文件内容
                    while ((len = bis.read(datas)) != -1) {
                        bos.write(datas, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                File copyFile = new File(dest, newFile.getName() + hz);
                newFile.renameTo(copyFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
