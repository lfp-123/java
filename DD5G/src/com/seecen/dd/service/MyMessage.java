package com.seecen.dd.service;

import com.seecen.dd.cons.CardUntil;
import com.seecen.dd.entity.MobileCard;



import java.io.*;



/**
 * @author ${林锋鹏}
 * @Title: MyException
 * @ProjectName Java
 * @date 2019/8/18 20:50
 */
public class MyMessage implements  Runnable {
    public  MyMessage(String number){
        MobileCard card = CardUntil.cards.get(number);
        card.setSystemmailbox(card.getSystemmailbox()+1);
    }

    @Override
    public void run() {
        while (true) {
            File file = new File("C:\\Users\\asus\\Desktop\\" +
                    "sc190701\\学员\\林锋鹏\\Java\\DD5G\\src\\系统消息.txt");
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("来自兜兜5G大厅的问候：尊贵的会员，恭喜你注册成功！");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
