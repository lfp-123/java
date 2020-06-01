package seecen;

import java.util.Scanner;

/**
 * �������Σ�ÿ�μ�һ����
 * @author Shaw
 * @date 2019/7/25 14:33
 */
public class Demo05 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("������ֱ��������������");
        int line = in.nextInt();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
