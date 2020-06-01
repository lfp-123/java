package seecen;

import java.util.Scanner;

/**
 * ����
 *
 * @author Shaw
 * @date 2019/7/25 14:14
 */
public class Demo02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("������Ҫ��ӡ��������");
        int line = in.nextInt();
        for (int i = line; i > 0; i--) {
            for (int k = i - 1; k > 0; k--) {
                System.out.print(" ");
            }
            for (int j = (line - i) * 2 + 1; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = line - 1; i > 0; i--) {
            for (int j = line - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = i * 2 - 1; k > 0; k--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
