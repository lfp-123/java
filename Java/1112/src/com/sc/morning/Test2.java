package com.sc.morning;

public class Test2 {

    public static void main(String[] args) {
        int[] ints = {1, 10, 5, 3, 6, 7};
        System.out.println("原数组：");
        for (int n:ints
                ) {
            System.out.print(n+" ");
        }
        for (int i = 0; i < ints.length-1; i++) {
            for (int j =0 ;j<ints.length-1-i;j++){
                if(ints[j]>ints[j+1]){
                    int temp = ints[j];
                    ints[j] = ints[j+1];
                    ints[j+1]=temp;
                }
            }
        }
        System.out.println("冒泡排序后：");
        for (int n:ints
             ) {
            System.out.print(n+" ");
        }
    }
}
