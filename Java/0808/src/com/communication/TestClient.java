package com.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author ${林锋鹏}
 * @Title: TestClient
 * @ProjectName Java
 * @date 2019/8/8 17:36
 */
public class TestClient {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try( Socket socket = new Socket("localhost",555)){
            System.err.println("已经连上服务器端！");
            while (true){
                System.err.println("请输入信息：");
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                String sendmessage =  input.readLine();
                writer.write(sendmessage+"\n");
                writer.flush();
                System.err.println("消息已经发送，等待服务器响应...");

                BufferedReader Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = Reader.readLine();
                System.err.println("客户端收到消息"+message);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
