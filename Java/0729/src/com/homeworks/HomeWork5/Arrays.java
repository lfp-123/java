package com.homeworks.HomeWork5;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Arrays {

    public void arrayInsert(int arr[],int start,int value){
        int j = arr.length-1;
        for (; j >=start; j--) {
            if(start==j){
                arr[start]=  value ;break;
            }
            arr[j] = arr[j-1];
        }

            }
    public void show(int arr[]){
        for (int a:arr) {
            System.out.print(a+"\t");
        }
    }
}
