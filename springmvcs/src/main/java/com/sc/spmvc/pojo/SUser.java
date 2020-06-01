package com.sc.spmvc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: SUSer
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/7 16:01
 */
public class SUser {
    private Integer id;
    private String username;
    private String password;
    private String head;

    /**
     * 如果转json格式出现日期对象需要加一个注解
     */
    @JsonFormat
    private Date time;
    private String[] ck;

    public SUser() {
    }

    public SUser(Integer id, String username, String password, String head, Date time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.head = head;
        this.time = time;
    }

    public String[] getCk() {
        return ck;
    }

    public void setCk(String[] ck) {
        this.ck = ck;
    }

    @Override
    public String toString() {
        return "SUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", head='" + head + '\'' +
                ", time=" + time +
                '}';
    }

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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
