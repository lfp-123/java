package cn.sc.morning;

import java.util.Scanner;

/**
 * @author asus
 * @Title: Test1
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/1311:41
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要格式化的数字:");
        String num = input.next();
        System.out.println("请输入要格式化的长度:");
        int len = input.nextInt();
        System.out.println("格式化的数字:");
        int values= len-num.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <values ; i++) {
            sb.append(0);
        }
        sb.append(num);
        System.out.println(sb);
    }
}
