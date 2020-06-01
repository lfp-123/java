import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/19 9:46
 */
public class Text1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tick =5000;
        double zhekou = 1;
        System.out.println("请输入要出行的月份：");
      int month  = scanner.nextInt();
        switch (month){
            case  3:
            case  4:
            case  5:
            case  6:
            case  8:
            case  10:System.out.println("旺季出发"); break;
            default:
                System.out.println("淡季出发");


        }
    }

}
