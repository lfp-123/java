package com.sc.morning;

public class Test3 {

    public static void main(String[] args) {

        int[] nums = new int[]{100,67,65,3,45,22,19,3};
        System.out.println("原数组为：");
        for (int n :
                nums) {
            System.out.print(n+" ");
        }
        System.out.println();
        System.out.println("选择排序：");
        for (int i = 0; i < nums.length-1; i++) {
            for (int j=i;j<nums.length-1;j++){
                if(nums[j+1]<nums[i]){
                    int temp = nums[i];
                    nums[i] = nums[j+1];
                    nums[j+1]=temp;
                }
            }
        }
        for (int n :
                nums) {
            System.out.print(n+" ");
        }
    }



}
