package com.homeworks.work3;

import java.util.Scanner;

/**
 * @author asus
 * @Title: Test3
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 19:44
 */
public class Test3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入课程代号（1-3）：");

        try {
            int num = scanner.nextInt();
            if (num == 1) {
                System.out.println("C语言！");
            } else if (num == 2) {
                System.out.println("java！");
            } else if (num == 3) {
                System.out.println("数据库！");
            }
            throw  new Exception("请输入正确数字：");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("欢迎提出建议！");
        }
    }
}
