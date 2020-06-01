import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 10:18
 */
public class Text1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 9};
        int[] num = solution(nums,3);
        for (int element:num) {
            System.out.print(element);
        }

    }



    public static int[] solution(int[] nums, int target) {
        int[] temp = new int[2];
        //用来存要找的数的下标
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    temp[0] = i;
                    temp[1] = j;

                }
            }
        }
                return temp;
    }
}