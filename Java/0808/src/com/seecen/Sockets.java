package com.seecen;


import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author ${林锋鹏}
 * @Title: Socket
 * @ProjectName Java
 * @date 2019/8/8 15:33
 */
public class Sockets {
    public static void main(String[] args) throws Exception {
        { //192.168.166.15


                try(Socket socket = new Socket("192.168.0.192", 5000)) {

                     OutputStreamWriter wt = new OutputStreamWriter(socket.getOutputStream());
                     wt.write("gggggg" );
                     wt.flush();

                    } catch (Exception e) {
                    e.printStackTrace();
                    }

        }



    }
}
