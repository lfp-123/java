package com.communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: TestChatServer
 * @ProjectName Java
 * @date 2019/8/8 16:56
 */
public class TestChatServer {

    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(555)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.err.println("服务器已经启动！,等待连接...");

            try (Socket accept = serverSocket.accept()) {
                System.err.println("服务器已连接" + "恭喜" + accept.getInetAddress() + "成功连接！");
                while (true){
                    BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                    String message = br.readLine();
                    System.err.println("服务器接收到消息：" + message);
                    //发送消息
                    OutputStreamWriter ow = new OutputStreamWriter(accept.getOutputStream());
                    System.err.println("服务器开始发送消息：");
                    System.out.println("请输入：");
                    String sendMessage = input.readLine();
                    ow.write(sendMessage + "\n");
                    ow.flush();
                    System.err.println("服务器已发送！");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
