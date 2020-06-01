package com.seecee.sc1911.pojo;

/**
 * @author ${林锋鹏}
 * @Title: HUserinfo
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/24 11:02
 */
public class HUserinfo {
    private long id;
    private String sex;
    private Long age;
    private String mess;
    private HUser user;

    @Override
    public String toString() {
        return "HUserinfo{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", mess='" + mess + '\'' +
                '}';
    }

    public HUser getUser() {
        return user;
    }

    public void setUser(HUser user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HUserinfo hUserinfo = (HUserinfo) o;

        if (id != hUserinfo.id) return false;
        if (sex != null ? !sex.equals(hUserinfo.sex) : hUserinfo.sex != null) return false;
        if (age != null ? !age.equals(hUserinfo.age) : hUserinfo.age != null) return false;
        if (mess != null ? !mess.equals(hUserinfo.mess) : hUserinfo.mess != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (mess != null ? mess.hashCode() : 0);
        return result;
    }
}
