package com.seencen;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Member[] member = new Member[4];
        MemberTake memberTake = new MemberTake();
        for (int i = 0; i <member.length ; i++) {
            System.out.print("请输入会员编号：");
            int num1 = scanner.nextInt();
            System.out.print("请输入会员积分：");
            int num2 = scanner.nextInt();
            //创建一个member的对象，可以用来获取member的值，id,score
            Member member1 = new Member();
            member1.member = num1;
            member1.score=num2;
            //将一整个members定义为数组 ，装很多个member对象，一个member中含有一个id一个score。
            member[i]=member1;
            // 然后将一个定义的数组 装进去里面有输入的数据
        }
        memberTake.showMember(member);
        System.out.print("请输入要查找的编号:");
        int num3 = scanner.nextInt();

       int a= memberTake.search(member,num3);
        System.out.print("他的积分是："+a);



    }
}
