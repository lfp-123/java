import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 18:43
 */
public class HomeWork2 {
    public static void main(String[] args) {
        System.out.println("请输入4家店的价格：");
        Scanner scanner = new Scanner(System.in);
        int i =1;
        int mid =0;
        int min =0;
        while (i<5){

            for (; i <5 ; i++) {
                System.out.print("第"+i+"家的价格：");
                int value = scanner.nextInt();
                if(mid==0){
                    min =value;
                    mid++;
                }
                if(value<min){
                    min=value;
                    mid++;
                }
            }


        }
        System.out.println("最低价格是："+min);
    }
}
