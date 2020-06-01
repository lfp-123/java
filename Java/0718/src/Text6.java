import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 15:55
 */
public class Text6 {
    public static void main(String[] args) {

        int luckNum = (int)((Math.random()*10));
        System.out.println(luckNum);
        System.out.println("请输入4位会员号：");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        if(num/10%10==luckNum){
            System.out.println("恭喜你");
        }else {
            System.out.println("很遗憾");
        }

    }
}
