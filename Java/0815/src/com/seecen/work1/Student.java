package com.seecen.work1;

/**
 * @author ${林锋鹏}
 * @Title: Student
 * @ProjectName Java
 * @date 2019/8/15 10:17
 */
public class Student extends People{

    public  Student() {
        System.out.println("执行了构造");
    }

    private  String name;
    private  int age;

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
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
