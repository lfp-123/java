/**
 * @author asus
 * @date 2019/7/23 18:52
 */
public class HomeWork3 {
    public static void main(String[] args) {
        int  s =0;
        int  k=0;
        int[] nums =new int[]{18,25,7,36,13,2,89,63};
        for (int i = 0; i <nums.length ; i++) {
            for (int j = 1; j < nums.length; j++) {
                if(nums[i]>nums[j]){
                        s=j;
                        k=nums[j];
                }
            }

        }
        System.out.println("最低积分是："+k);
        System.out.println("最低积分的下标是："+s);
    }
}
