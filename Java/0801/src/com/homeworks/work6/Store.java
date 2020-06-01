package com.homeworks.work6;

/**
 *  �����̵��ࡣ
 * @author ˼�ϿƼ�
 */
class Store{
	/**
	 * �û������
	 * @param choice �û�ѡ��
	 * @return ����
	 */
	public static Animal get( String choice ) { 
		if ( choice.equalsIgnoreCase( "dog" )) { 
			return new Dog(); 
		} else if ( choice.equalsIgnoreCase( "pig" )) { 
			return new Pig(); 
		} else
			return new Cat(); 
	} 
}