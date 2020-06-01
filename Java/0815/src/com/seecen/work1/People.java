package com.seecen.work1;

/**
 * @author ${林锋鹏}
 * @Title: People
 * @ProjectName Java
 * @date 2019/8/15 10:43
 */
public class People {
    private  double height;
    private  String gender;

    public People() {

    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public People(double height, String gender) {
        this.height = height;
        this.gender = gender;
    }
}
