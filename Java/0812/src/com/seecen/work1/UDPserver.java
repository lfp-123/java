package com.seecen.work1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * @author ${林锋鹏}
 * @Title: UDPserver
 * @ProjectName Java
 * @date 2019/8/12 11:06
 */
public class UDPserver {
    public static void main(String[] args) {


            try (DatagramSocket socket = new DatagramSocket(9527)) {
                byte[] datas = new byte[1024 * 512];
                //创建一个包用来缓存数据
                DatagramPacket datagramSocket1 = new DatagramPacket(datas, 0, datas.length);
                //阻塞等一个数据包过来
                while (true) {
                    socket.receive(datagramSocket1);

                    String s = new String(datagramSocket1.getData(),
                            0, datagramSocket1.getLength());
                    System.out.println(s);


                    byte[] bytes = "收到了。".getBytes();
                    DatagramPacket localhost = new DatagramPacket(bytes, 0,
                            bytes.length, datagramSocket1.getAddress(), datagramSocket1.getPort());
                    socket.send(localhost);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}
