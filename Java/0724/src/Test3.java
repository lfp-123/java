package src;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] oldGral = new int[5];
        int[] newGral = new int[5];
        System.out.println("请输入5位会员的积分");
        for (int i = 0; i < oldGral.length; i++) {
            System.out.print("第" + (i + 1) + "位会员积分：");
            oldGral[i] = input.nextInt();
            newGral[i] = oldGral[i] + 500;
        }
        System.out.println("序号\t\t历史积分\t\t新年积分 ");
        for (int i = 0; i < oldGral.length; i++) {
            System.out.println(i + "\t\t"
                    + oldGral[i]
                    + "\t\t" + newGral[i]);
        }

    }
}
