package com.homeworks.HomeWork1;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Test {
    public static void main(String[] args) {
        meun meun = new meun();
        Scanner scanner = new Scanner(System.in);
        Check check = new Check();
        while (true){
            meun.show();
            int input =scanner.nextInt();
            if(input==1){
                meun.inputUserName();
                String User= scanner.next();
                meun.inputPassword();
                String password = scanner.next();
                check.check(User,password);
            }else if(input ==2){
                break;
            }

        }
    }

}
