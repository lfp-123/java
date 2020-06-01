package com.homeworks.work4;

/**
 * @author asus
 * @Title: Age
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 19:51
 */
public class Age {
    private   int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {

        if (age< 1 || age >100) {

            throw new Exception("必须要在1-100之间");

        }
        this.age = age;

    }
}
