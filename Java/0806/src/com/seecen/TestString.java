package com.seecen;

/**
 * @Author bigpig
 * @Date 2019/8/6 14:54
 * @Description
 */
public class TestString {
    public static void main(String[] args) {
        String str1 = new String("hello");
        String str2 = new String("hello");
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());

    }
}
