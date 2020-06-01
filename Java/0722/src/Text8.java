import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 16:58
 */
public class Text8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num>0){
            int a=num%10;
            num= num/10;
            System.out.print(a);
        }
    }
}
