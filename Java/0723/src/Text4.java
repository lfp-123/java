import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 16:05
 */
public class Text4 {
    public static void main(String[] args) {
        int[] nums = new int[]{8,4,2,1,23,344,12};
        int s=0;
        boolean a =true;
        for (int i = 0; i <nums.length ; i++) {
            System.out.print(nums[i]+"\t");
            s+= nums[i];

        }

        System.out.println("数组的和为："+s);
        while(a) {
        System.out.print("请输入一个数判断是否存在于数组中：");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
            for (int i = 0; i < nums.length; i++) {
                if (num == nums[i]) {
                    System.out.println("你猜对了");
                    a = false;
                }else {
                    System.out.println("你猜错了");
                }
            }
        }
    }
}
