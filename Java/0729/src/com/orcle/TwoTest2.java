import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/29 11:01
 */
public class TwoTest2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Test2 test2 = new Test2();
        System.out.print("请输入Java成绩：");
        int i = scanner.nextInt();
        System.out.print("请输入成绩：");
        int i1 = scanner.nextInt();
        System.out.print("请输入DB成绩：");
        int i2 = scanner.nextInt();
        System.out.print("总成绩：");
        System.out.println(test2.allScore(i,i1,i2));
        System.out.print("平均成绩：");
        System.out.println(test2.avg(i,i1,i2));


    }
}
