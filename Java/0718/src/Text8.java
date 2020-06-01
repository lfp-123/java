import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 17:50
 */
public class Text8 {
    public static void main(String[] args) {
        System.out.println("请输入你的会员积分：");
        Scanner input = new Scanner(System.in);
        int money = input.nextInt();
        if(money<2000){
            System.out.println("8折");
        }else if(money>=2000&&money<4000){
            System.out.println("7折");
        }else if(money>=4000&&money<8000){
            System.out.println("6折");
        }if(money>=8000){
            System.out.println("5折");
        }
    }
}
