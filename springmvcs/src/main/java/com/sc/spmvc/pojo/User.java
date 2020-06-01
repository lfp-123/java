package com.sc.spmvc.pojo;

import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: User
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/2/7 15:10
 */
public class User {
    private String username;
    private String age;
    private Date time;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", time=" + time +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
