package com.seecen;

/**
 * @Author bigpig
 * @Date 2019/8/6 9:49
 * @Description
 */
public class Student extends People {
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return (this.stuNo == other.stuNo &&
        this.username.equals(other.username));
    }
    @Override
    public int hashCode() {
        return this.stuNo * 100 - 5 * this.username.hashCode();
    }

    private String username;
    private int stuNo;
    public Student(String username, int stuNo) {
        this.username = username;
        this.stuNo = stuNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public void printer() {
        System.out.println("Student{" +
                "username='" + username + '\'' +
                ", stuNo=" + stuNo +
                '}');
    }


}
