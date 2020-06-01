import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 19:07
 */
public class HomeWork5 {
    public static void main(String[] args) {
        int i=0;
        String[] strings = new String[5];
        System.out.println("请输入五句话：");
        for (int j = 0; j <5 ; j++) {
            System.out.print("请输入第"+(j+1)+"句话：");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            strings[j] = str;
        }
        System.out.println("逆序输出这些话：");
        for (int j = strings.length-1; j >=0 ; j--) {
            System.out.println(strings[j]);
        }

    }
}
