package com.seecee.sc1911.pojo;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: HDept
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/24 11:02
 */
public class HDept {
    private long id;
    private String deptname;
    private List<HUser> listUser;

    public List<HUser> getListUser() {
        return listUser;
    }

    public void setListUser(List<HUser> listUser) {
        this.listUser = listUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HDept hDept = (HDept) o;

        if (id != hDept.id) return false;
        if (deptname != null ? !deptname.equals(hDept.deptname) : hDept.deptname != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "HDept{" +
                "id=" + id +
                ", deptname='" + deptname + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (deptname != null ? deptname.hashCode() : 0);
        return result;
    }
}
