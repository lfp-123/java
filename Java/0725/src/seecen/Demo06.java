package seecen;

import java.util.Scanner;

/**
 * @author Shaw
 * @date 2019/7/25 15:52
 */
public class Demo06 {
    private static final String YES = "y", NO = "n";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cloth = 0;
        for (int i = 1; i < 6; i++) {
            System.out.println("��ӭ����" + i + "��ר����");
            do {
                System.out.print("Ҫ�뿪�y/n����");
                String input = in.next();
                if (YES.equals(input)) {
                    System.out.println("������\n");
                    break;
                }
                if (NO.equals(input)) {
                    cloth++;
                    System.out.println("����һ���·�");
                }
            } while (true);
        }
        System.out.print("�ܹ�����" + cloth + "���·�");
    }
}
