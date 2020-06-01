import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 15:35
 */
public class Text3 {
    public static void main(String[] args) {
        int[] arr=new int[5];
        int j=1;
        double s= 0;
        while (j<6){
            System.out.print("请输入"+j+"位学员的成绩：");
            Scanner scanner = new Scanner(System.in);
            int score = scanner.nextInt();
            arr[j-1] = score;
            j++;
        }


        for (int i = 0; i <arr.length ; i++) {
               s+= arr[i];


        }
        System.out.println("总成绩是："+s/arr.length);

    }

}
