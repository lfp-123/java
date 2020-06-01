package com.seecen.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class ORole implements Serializable {
    private Long id;

    private String rolename;

    private String explain;

    private String status;

    private Long operator;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rolename=").append(rolename);
        sb.append(", explain=").append(explain);
        sb.append(", status=").append(status);
        sb.append(", operator=").append(operator);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}