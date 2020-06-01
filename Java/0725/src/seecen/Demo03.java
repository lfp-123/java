package seecen;

import java.util.Scanner;

/**
 * �������Σ��Ȳ����Ϊ2��
 * @author Shaw
 * @date 2019/7/25 14:26
 */
public class Demo03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("������ֱ�������ε�������");
        int line = in.nextInt();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < i * 2 + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
