package com.homeworks.HomeWork4;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class addVip {
    int[] vip = new int[4];
    int[] score = new int[4];
    public void addVip(int id,int scores){
        for (int i = 0; i <vip.length; i++) {
            if(vip[i]==0){
                vip[i]= id;
                score[i]=scores;
                break;
            }
        }
    }
    public void showVipScore(){
        for (int i = 0; i <vip.length ; i++) {
            System.out.print(vip[i]+"\t"+score[i]);
            System.out.println();
        }
    }
    public void searchVipSore(int vips){
            for (int i = 0; i <vip.length ; i++) {
                if(vips==vip[i]){
                    System.out.println("该会员的积分为："+score[i]);break;
                }
        }
    }
}
