package com.seecee.sc1911.pojo;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: HTeacher
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/29 16:38
 */
public class HTeacher {
    private long id;
    private String name;

    private List<HStudent> listStudent;

    public List<HStudent> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<HStudent> listStudent) {
        this.listStudent = listStudent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HTeacher hTeacher = (HTeacher) o;

        if (id != hTeacher.id) return false;
        if (name != null ? !name.equals(hTeacher.name) : hTeacher.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
