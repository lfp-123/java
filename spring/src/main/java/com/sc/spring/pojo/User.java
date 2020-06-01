package com.sc.spring.pojo;

import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: User
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/12 15:49
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String money;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money='" + money + '\'' +
                ", time=" + time +
                '}';
    }
}
