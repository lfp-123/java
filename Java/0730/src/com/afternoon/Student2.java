package com.afternoon;


class Student2 {
	private String name;
	private int age;
	private String sex;
	private String subject;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 16) {
			System.out.println("错误！您还不符合最小年龄要求！");
		}
		this.age = age;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}

	public Student2(String name, int age) {
		this.name = name;
		this.age = age;
		this.sex = "男";
		this.subject = "assd";
	}

	public Student2(String name, int age, String sex, String subject) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.subject = subject;
	}

	public String introduction() {
		return "大家好！我是" + name + "，我今年" + age + "岁,性别是" + sex + ",专业是"
				+ subject;
	}
}
