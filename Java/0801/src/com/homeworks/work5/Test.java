package com.homeworks.work5;

/**
 * �����ࡣ
 * @author ˼�ϿƼ�
 */
public class Test {
	public static void main(String[] args) {
		Dog dog = new Dog("ŷŷ", "ѩ����");
		// 1�����Է���
		dog.eat();
		// 2������ӷ�����Ϸ��
		dog.catchingFlyDisc();
		Penguin pgn = new Penguin("��", "Q��");
		// 3�������Ӿ��
		pgn.swim();
		// 4����������Ϣ��
		pgn.print();
	}
}
