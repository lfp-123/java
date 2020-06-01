package cn.sc.homework;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 17:20
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuffer sbu = new StringBuffer();
        System.out.println("请输入你要格式化的数字：");
        String nums = input.next();
        int longs=  nums.length();
        sbu.append(nums);
        for (int i = longs-3; i >0; i-=3) {
            sbu.insert(i,",");
            longs--;
        }
        System.out.println(sbu);
    }
}
