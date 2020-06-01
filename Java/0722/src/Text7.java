import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 16:08
 */
public class Text7 {
    public static void main(String[] args) {
        boolean bool = true;
      do{  System.out.println("**********************");
          System.out.println("\t\t1.客户信息管理");
          System.out.println("\t\t2.购物结算");
          System.out.println("\t\t3.真情回馈");
          System.out.println("\t\t4.注销");
          System.out.println("**********************");
          System.out.print("请输入数字：");
      }while (false);
        do {
            Scanner scanner = new Scanner(System.in);
            int num =scanner.nextInt();
            switch (num) {
                case 1:
                    System.out.println("\t\t1.客户信息管理");
                    bool = false;
                    break;
                case 2:
                    System.out.println("\t\t2.购物结算");
                    bool = false;
                    break;
                case 3:
                    System.out.println("\t\t3.真情回馈");
                    bool = false;
                    break;
                case 4:
                    System.out.println("\t\t4.注销");
                    bool = false;
                    break;
                default:
                    System.out.print("输入错误，请重新输入：");
            }
        }while (bool);
    }
}
