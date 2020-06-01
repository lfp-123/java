package com.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: TestFileSever
 * @ProjectName Java
 * @date 2019/8/8 19:22
 */
public class TestFileSever {
    public static void main(String[] args) {
        try( ServerSocket serverSocket = new ServerSocket(5555)){
            System.out.println("服务器已经开启，等待连接...");
            while (true) {
            try(Socket accept = serverSocket.accept()) {

                    System.err.println("已经连接上");
                    //收到的文件数据
                    File file = new File("G:a.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader
                            (accept.getInputStream()), 1024);
                    BufferedWriter wt = new BufferedWriter(fw, 1024);
                    PrintWriter pw = new PrintWriter(wt);
                    while (true) {
                        String str = br.readLine();
                        if (str == null) {
                            break;
                        }
                        pw.println(str);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
