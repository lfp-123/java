package com.nit;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Testr
 * @ProjectName Java
 * @date 2019/9/25 22:09
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入大于1的整数：");
        int m = scanner.nextInt();
        int s=1;int i;
        int s1=0;
        for (i=1;s<m;i++){
            s1=s;
            s=s*i;

        }
        System.out.println("s="+s1+"\t\t"+"n="+(i-2));
    }
}
