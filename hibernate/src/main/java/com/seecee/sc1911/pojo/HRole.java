package com.seecee.sc1911.pojo;

/**
 * @author ${林锋鹏}
 * @Title: HRole
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/24 11:02
 */
public class HRole {
    private long id;
    private Long rolecode;
    private String rolename;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getRolecode() {
        return rolecode;
    }

    public void setRolecode(Long rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HRole hRole = (HRole) o;

        if (id != hRole.id) return false;
        if (rolecode != null ? !rolecode.equals(hRole.rolecode) : hRole.rolecode != null) return false;
        if (rolename != null ? !rolename.equals(hRole.rolename) : hRole.rolename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (rolecode != null ? rolecode.hashCode() : 0);
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
    }
}
