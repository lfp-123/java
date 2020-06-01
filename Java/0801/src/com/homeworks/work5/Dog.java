package com.homeworks.work5;

/**
 * ���ࡣ
 * @author ˼�ϿƼ�
 */
public class Dog extends Pet implements Eatable, FlyingDiscCatchable {
	private String strain="ѩ����";// Ʒ��
	/**
	 * �вι��췽����
	 * @param name �ǳ�
	 * @param strain Ʒ��
	 */
	public Dog(String name, String strain) {
		super(name);
		this.strain = strain;
	}
	/**
	 * ��ȡƷ�֡�
	 * @return Ʒ��
	 */
	public String getStrain() {
		return strain;
	}
	
	public void print() {
		System.out.println("������԰ף�\n�ҵ����ֽ�" + this.name + "���ҵĽ���ֵ��"
				+ this.health + "���Һ����˵����̶ܳ���" + this.love + "��");
	}
	
	public void eat() {
		super.health = super.health + 3;
		System.out.println("����" + super.name + "�Ա���������ֵ����3��");
	}

	public void catchingFlyDisc() {
		System.out.println("����" + super.name + "���ڽӷ��̡�");
		super.health = super.health - 10;
		super.love = super.love + 5;
	}

}
