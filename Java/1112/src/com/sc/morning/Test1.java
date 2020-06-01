package com.sc.morning;


import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/11/12 9:18
 */
public class Test1 {
    public static void main(String[] args) {
        String[] str = new String[]{"11","22","33","44","55"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要插入的位置：");
        int i1 = scanner.nextInt();
        System.out.println("请输入你要插入的元素：");
        String next = scanner.next();
        String[] strs = new String[str.length + 1];
        for (int i = 0,j=0; i < strs.length; i++,j++) {
            if (i==i1){
                strs[i]=next;
                i++;
            }
            strs[i] = str[j];
        }
        for (String each:
             strs) {
            System.out.println(each);
        }
    }
}
