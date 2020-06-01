package com.work1;

public class Penguin extends Pet {
    public Penguin() { }
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void introduce() {
        System.out.println("我是企鹅,我的性别是" + this.getGender());
    }
}
