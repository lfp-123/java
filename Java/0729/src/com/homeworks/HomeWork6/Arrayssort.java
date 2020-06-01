package com.homeworks.HomeWork6;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Arrayssort {
    public int Max(int[] arr ){
        int max =0;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]>max){
               max =  arr[i];
            }
        }
        return max;
    }
    public int min(int[] arr){
        int min=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return  min;
    }
    public void show(int[] arr){
        for (int a:arr) {
            System.out.print(a+"\t");
        }
    }


}
