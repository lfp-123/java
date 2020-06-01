package com.homeworks.work5;

/**
 * ���� �����ࡣ
 * @author ˼�ϿƼ�
 */
public abstract class Pet {
	protected String name = "������";// �ǳ�
	protected int health = 100;// ����ֵ
	protected int love = 0;// ���ܶ�
	/**
	 * �вι��췽����
	 * @param name �ǳ�
	 */
	public Pet(String name) {
		this.name = name;
	}
	/**
	 * ��ȡ�ǳơ�
	 * @return �ǳ�
	 */
	public String getName() {
		return name;
	}
	/**
	 * ��ȡ����ֵ��
	 * @return ����ֵ
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * ��ȡ���ܶȡ�
	 * @return ���ܶ�
	 */
	public int getLove() {
		return love;
	}
	/**
	 *  ���������Ϣ��
	 */
	public abstract void print();
}
