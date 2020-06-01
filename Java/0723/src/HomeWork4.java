import java.util.Arrays;

/**
 * @author asus
 * @date 2019/7/23 18:57
 */
public class HomeWork4 {
    public static void main(String[] args) {
      int[] nums =new int[]{1,3,-1,5,-2};
        System.out.print("原数组为：");
        for ( int a:nums) {
            System.out.print(a+"\t");
        }
        System.out.println();
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]<0){
                nums[i]=0;
            }
        }
        System.out.print("");
        System.out.print("逆序并处理后：");
        for (int i = nums.length-1; i >=0 ; i--) {
            System.out.print(nums[i]+"\t");
        }



    }
}
