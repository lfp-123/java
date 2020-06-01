package cn.sc.afternoon;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 15:55
 */
public class Test1 {
    public static void main(String[] args) {
        int index =0;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你的字符：");
        String Strings = input.nextLine();
        System.out.println("请输入你要查找的字符的位置");
        String num = input.nextLine();
        int length = Strings.length();
        while (true) {
            int value = Strings.indexOf(num, index);

            if(value==-1){
                break;
            }
            System.out.print(value+" ");
            index = index+value;
        }
    }
}
