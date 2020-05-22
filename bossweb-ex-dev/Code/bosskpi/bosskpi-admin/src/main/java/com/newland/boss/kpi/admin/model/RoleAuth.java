package com.newland.boss.kpi.admin.model;

import java.util.Date;
/**
 * 角色资源关系
 * @author ZJUNE
 *
 */
public class RoleAuth {
	
	private Integer roleId;
	private Integer resourceId;
	private Integer status;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyOperatorId;
	
	public RoleAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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
		return "RoleAuth [roleId=" + roleId + ", resourceId=" + resourceId + ", status=" + status + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId + "]";
	} 
	
	
	
}
