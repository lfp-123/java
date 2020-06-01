import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 18:56
 */
public class HomeWork5 {
    public static void main(String[] args) {
        int i,j=0;
        double rate ;

        for(i=1;i<=10;i++){
            System.out.print("请输入第"+i+"位的年龄：");
            Scanner scanner = new Scanner(System.in);
            int old = scanner.nextInt();
            if(old>30){
                j++;
            }
        }
        rate = j%10*10.0;

        System.out.println("30岁以上的比例为："+rate+"%");
        System.out.println("30岁以下的比例为："+(100-rate)+"%");

    }
}
