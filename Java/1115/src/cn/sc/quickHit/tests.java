package cn.sc.quickHit;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: tests
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/15 15:27
 */
public class tests {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
        int timeLimit = LevelParam.levels[1].getTimeLimit();
        long l  = System.currentTimeMillis();
        System.out.println(timeLimit);
        System.out.println(l1);
        System.out.println(l);
        System.out.println((l-l1)/1000);
    }
}
