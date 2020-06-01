package com.homework;

import java.io.*;

import java.net.Socket;


/**
 * @author ${林锋鹏}
 * @Title: TestFileClint
 * @ProjectName Java
 * @date 2019/8/8 19:36
 */
public class TestFileClient {
    public static void main(String[] args) throws Exception {
        System.err.println("已经连上了！");
        while (true) {
            try (Socket socket = new Socket("localhost", 5555)) {



                File file = new File("H:a.txt");
                BufferedReader br = new BufferedReader(new FileReader(file), 1024);

                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

                String str = br.readLine();

                writer.write(str + "\n");
                writer.flush();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
