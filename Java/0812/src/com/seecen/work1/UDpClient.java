package com.seecen.work1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author ${林锋鹏}
 * @Title: UDpClient
 * @ProjectName Java
 * @date 2019/8/12 11:14
 */
public class UDpClient {
    public static void main(String[] args) {

            try (DatagramSocket sorket = new DatagramSocket()) {
                byte[] datas = "你好！！！".getBytes();
                //构建一个发送包
                DatagramPacket sendPacket = new DatagramPacket(datas, 0, datas.length,
                        InetAddress.getByName("192.168.0.103"), 9527);

                //发送数据包
                sorket.send(sendPacket);


                byte[] data = new byte[1024 * 512];
                DatagramPacket datagramSocket1 = new DatagramPacket(data, 0, datas.length);
                sorket.receive(datagramSocket1);
                String s = new String(datagramSocket1.getData(),
                        0, datagramSocket1.getLength());
                System.out.println(s);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
