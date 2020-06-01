package homework;

import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 18:57
 */
public class Text1 {

    public static void main(String[] args) {
        System.out.println("请输入一个数字：");
        Scanner num = new Scanner(System.in);
        int newNum = num.nextInt();
        if(newNum%3==0||newNum%5==0){
            System.out.println("该数能被3 或 5 整除");
        }else {
            System.out.println("该数不能被3 或 5 整除");
        }

    }

}
