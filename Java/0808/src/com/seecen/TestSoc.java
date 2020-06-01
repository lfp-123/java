package com.seecen;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: TestSoc
 * @ProjectName Java
 * @date 2019/8/8 15:58
 */
public class TestSoc {


    public static void main(String[] args) throws Exception {

        // 创建服务器socket的server对象
        try (ServerSocket server = new ServerSocket(5000)) {
            while (true) {
                // 等待客户端连我
                try (Socket socket = server.accept()) {
                    // 执行到这个位置,代表已经连上
                    InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                   BufferedReader br = new BufferedReader(reader);
                    System.out.println(socket);
                    String  str = br.readLine();
                   // int str = reader.read();
                    System.out.println(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
