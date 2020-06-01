package com;

import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/26 15:36
 */
public class TestPassworld {

    public static void main(String[] args) {
        boolean a =true;
        boolean b =true;
        Scanner scanner = new Scanner(System.in);
        PassWord passWord = new PassWord();
        while (b) {


            System.out.print("请输入用户名：");
            String user = scanner.next();
            passWord.Username = user;

            System.out.print("请输入密码：");
            String password = scanner.next();
            passWord.password = password;
              b= !passWord.login();
        }

        while (a) {
            System.out.print("请输入新密码：");
            String newpasswords = scanner.next();
            System.out.print("请再次输入新密码：");
            String newpasswords1 = scanner.next();
            a= passWord.show(newpasswords, newpasswords1);
        }
    }

}


