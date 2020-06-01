import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 16:48
 */
public class Text5 {
    public static void main(String[] args) {
        int[] arr=new int[5];
        int k=1;
        double s= 0;
        Scanner scanner = new Scanner(System.in);
        while (k<6){
            System.out.print("请输入第"+k+"笔的购物金额：");
            int score = scanner.nextInt();
            arr[k-1] = score;
            k++;
        }
        System.out.println("序号"+"\t\t金额");
        for (int i = 0; i <arr.length ; i++) {
            System.out.println((i+1)+"\t\t"+arr[i]);
            s+=arr[i];

        }
        System.out.println("总金额"+"\t"+s);

    }
}
