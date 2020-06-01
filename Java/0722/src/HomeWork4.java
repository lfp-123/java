import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 18:43
 */
public class HomeWork4 {
    public static void main(String[] args) {
       boolean judge = true;
        while (judge) {
            System.out.print("请输入数字1-7，输入0结束：");
            Scanner scanner = new Scanner(System.in);
            int date = scanner.nextInt();
            switch (date) {
                case 1:
                    System.out.println("今天是\tMON");
                    break;
                case 2:
                    System.out.println("今天是\tTHE");
                    break;
                case 3:
                    System.out.println("今天是\tWED");
                    break;
                case 4:
                    System.out.println("今天是\tTHU");
                    break;
                case 5:
                    System.out.println("今天是\tFRI");
                    break;
                case 6:
                    System.out.println("今天是\tSUN");
                    break;
                case 7:
                    System.out.println("今天是\tSUN");
                    break;
                default:
                    System.out.println("程序结束");
                    judge =false;
                    break;
            }
        }
    }
}
