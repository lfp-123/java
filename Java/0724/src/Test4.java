package src;

/**
 * 冒泡排序法
 * N = 数组.length
 * 外层循环 N-1
 * 内存循环 N-1-i
 */
public class Test4 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 1, 3, 8, 9, 5, 4, 7};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if(nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        // 打印出看结果
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+",");
        }

    }
}
