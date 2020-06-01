import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 17:04
 */
public class Text9 {
    public static void main(String[] args) {
        System.out.println("请输入一个数：");
        System.out.println("根据这个数的值可以输出加法表：");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int b=num;
        int a =0;
        int s=num;
        for (int i = 0; i <= b ; i++) {
            System.out.print(a);
            System.out.print("+");
            System.out.print(num);
            System.out.print("=");
            System.out.println(s);
                a++;
                num--;
                s= a+num;
        }

    }
}
