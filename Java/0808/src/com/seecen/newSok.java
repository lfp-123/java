package com.seecen;

import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: newSok
 * @ProjectName Java
 * @date 2019/8/8 16:02
 */
public class newSok {
    public static void main(String[] args) throws Exception {
        {
            Socket socket = new Socket("192.168.166.15", 9527);
            while (true) {
                try {
                    OutputStreamWriter wt = new OutputStreamWriter(socket.getOutputStream());
                    wt.write("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
