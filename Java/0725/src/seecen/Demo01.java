package seecen;

import java.util.Scanner;

/**
 * @author Shaw
 * @date 2019/7/25 10:03
 */
public class Demo01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int classNum = 3;
        int studentNum = 4;
        int excellent = 0;
        for (int i = 0; i < classNum; i++) {
            double[] score = new double[studentNum];
            double sum = 0;
            System.out.println("�������" + (i + 1) + "���༶�ĳɼ�");
            for (int j = 0; j < studentNum; j++) {
                System.out.print("�������" + (j + 1) + "��ѧ���ĳɼ�:");
                score[j] = in.nextDouble();
                sum += score[j];
                if (score[j] > 85) {
                    excellent++;
                }
            }
            System.out.println("��" + (i + 1) + "���༶��ƽ�����ǣ�" + sum / studentNum);
            System.out.println();
        }
        System.out.print("�ɼ�85�����ϵ�ѧԱ������" + excellent + "��");
    }
}
