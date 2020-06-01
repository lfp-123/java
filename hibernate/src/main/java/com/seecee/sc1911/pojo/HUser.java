package com.seecee.sc1911.pojo;


import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: HUser
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/24 11:02
 */
public class HUser {
    private long id;
    private String username;
    private String password;
    private Date createtime;
    private HDept dept;
    private HUser u;
    private HUserinfo info;
    public HUserinfo getInfo() {
        return info;
    }

    public void setInfo(HUserinfo info) {
        this.info = info;
    }

    public HUser getU() {
        return u;
    }

    public void setU(HUser u) {
        this.u = u;
    }

    public HDept getDept() {
        return dept;
    }

    public void setDept(HDept dept) {
        this.dept = dept;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HUser hUser = (HUser) o;

        if (id != hUser.id) return false;
        if (username != null ? !username.equals(hUser.username) : hUser.username != null) return false;
        if (password != null ? !password.equals(hUser.password) : hUser.password != null) return false;
        if (createtime != null ? !createtime.equals(hUser.createtime) : hUser.createtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }

//    @Override
//    public String toString() {
//        return "HUser{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", createtime=" + createtime +
//
//                '}';
//    }
}
