package com.seecee.sc1911.pojo;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: HStudent
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/29 16:38
 */
public class HStudent {
    private long id;
    private String name;
    private List<HTeacher> listTeacher;

    public List<HTeacher> getListTeacher() {
        return listTeacher;
    }

    public void setListTeacher(List<HTeacher> listTeacher) {
        this.listTeacher = listTeacher;
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

        HStudent hStudent = (HStudent) o;

        if (id != hStudent.id) return false;
        if (name != null ? !name.equals(hStudent.name) : hStudent.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
