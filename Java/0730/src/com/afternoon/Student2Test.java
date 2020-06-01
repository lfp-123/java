package com.afternoon;

/**
 * �����ࡣ
 * @author ˼�ϿƼ�
 */
public class Student2Test {
	public static void main(String[] args) {
		Student2 s1 = new Student2("张三", 20);
		Student2 s2 = new Student2("李四", 22, "女", "BENET");
		System.out.println(s1.introduction());
		System.out.println(s2.introduction());
	}

}