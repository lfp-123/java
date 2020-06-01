package com.homeworks.HomeWork3;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class addVip {
    int[] vip = new int[1];
    int[] score = new int[1];
        public void addVip(int id,int scores){
            for (int i = 0; i <vip.length; i++) {
                vip[i]= id;
                score[i]=scores;
            }
        }
        public void showVipScore(){
            for (int i = 0; i <vip.length ; i++) {
                System.out.print(vip[i]+"\t"+score[i]);
                System.out.println();
            }
        }

}
