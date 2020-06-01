package com.homeworks.work5;

/**
 * 企鹅类。
 * @author 思诚科技
 */
public class Penguin extends Pet implements Eatable, Swimmable {
	private String sex ="Q仔";//性别
	/**
	 * 有参构造方法。
	 * @param name 昵称
	 * @param sex 性别
	 */
	public Penguin(String name, String sex) {
		super(name);
		this.sex = sex;
	}
	/**
	 * 获取性别。
	 * @return 性别
	 */
	public String getSex() {
		return sex;
	}

	public void print() {
		System.out.println("宠物的自白：\n我的名字叫" + this.name +
				"，我的健康值是"	+ this.health + "，我和主人的亲密程度是"
				+ this.love + ",我的性别是"+this.sex+ "。");
	}

	public void eat() {
		super.health = super.health + 5;
		System.out.println("企鹅" + super.name + "吃饱啦！健康值增加5。");
	}

	public void swim() {
		System.out.println("企鹅" + super.name + "正在游泳。");
		super.health = super.health - 10;
		super.love = super.love + 5;
	}
}
