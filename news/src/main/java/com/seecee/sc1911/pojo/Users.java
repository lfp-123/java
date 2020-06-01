package com.seecee.sc1911.pojo;

/**
 * @author ${林锋鹏}
 * @Title: Users
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/20 14:54
 */
public class Users {
    private Integer id;
    private String name;
    private String pw;

    public Users(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
