
import java.util.Scanner;

public class Text3 {
    public static void main(String[] args) {
        System.out.println("请输入4位会员卡号");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int s=0;
        int a ;
        int b = 10;
        int c=10;
        while (num>0){
            a = num % b;
            s = s + a;
            num = num / c;
        }
        System.out.println(s);
    }
}
