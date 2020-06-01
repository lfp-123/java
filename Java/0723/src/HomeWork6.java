import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 19:15
 */
public class HomeWork6 {
    public static void main(String[] args) {
        System.out.println("请输入十个数：");
        int  index =0;
        int  index1 =0;
        int  index2 =0;
        int[] nums = new int[10];
        for (int i = 0; i <10 ; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("第"+(i+1)+"个数：");
            int input = scanner.nextInt();
            nums[i]= input;
        }
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]==1){
                index++;
            }
            if(nums[i]==2){
                index1++;
            }
            if(nums[i]==3){
                index2++;
            }
        }
        System.out.println("数字 1 的个数："+index);
        System.out.println("数字 2 的个数："+index1);
        System.out.println("数字 3 的个数："+index2);
        System.out.println("非法数字的个数："+(nums.length-index-index1-index2));


    }
}
