package homework;

import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 19:09
 */
public class Text3 {
    public static void main(String[] args) {

        System.out.println("飞往非洲的飞机机票价格原价为5000");
        System.out.println("请输入你要出行的月份");
        Scanner go = new Scanner(System.in);
        int readyGo = go.nextInt();
        if(readyGo==5||readyGo==7||readyGo==6||readyGo==8||readyGo==9||readyGo==10){
            System.out.println("你选择出行的月份属于旺季");
            System.out.println("你能享受的折扣是 ：头等舱 9折" + "\t经济舱7.5折");
        }else {
            System.out.println("你选择出行的月份属于淡季");
            System.out.println("你能享受的折扣是：头等舱6折\t经济舱3折");
        }
    }
}
