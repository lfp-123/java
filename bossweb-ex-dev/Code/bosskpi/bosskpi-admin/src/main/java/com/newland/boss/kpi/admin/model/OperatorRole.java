package com.newland.boss.kpi.admin.model;

import java.util.Date;

//操作员管理角色
public class OperatorRole {
	
	private Integer operatorId;
	private Integer roleId;
	private Integer status;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyOperatorId;
	
	public OperatorRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyOperatorId() {
		return modifyOperatorId;
	}
	public void setModifyOperatorId(Integer modifyOperatorId) {
		this.modifyOperatorId = modifyOperatorId;
	}

	
	@Override
	public String toString() {
		return "OperatorRole [operatorId=" + operatorId + ", roleId=" + roleId + ", status=" + status + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId + "]";
	}
	
	
	
}
