package com.seencen;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class MemberTake {
 /**
  * 定义形参为meber类型数组
  **/
    public void showMember(Member[] member){
        System.out.println("***会员列表***");
        System.out.println("编号\t\t"+"积分");
        for (Member a : member) {
            System.out.println(a.member+"\t\t"+a.score);
        }
    }
    public int search(Member[] member,int menberid){
        for (int i = 0; i <member.length ; i++) {
            if((menberid-1)==i) {
                return member[menberid].score;
            }
        }
        return 0;

    }
}
