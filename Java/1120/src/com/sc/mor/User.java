package com.sc.mor;

/**
 * @author ${林锋鹏}
 * @Title: User
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/20 11:16
 */
public class User {
    private Integer age;
    private String name;
    private String gender;
    private String role;

    public User(Integer age, String name, String gender, String role) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
