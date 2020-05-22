package com.newland.boss.kpi.admin.model;

import java.util.List;

public class Role {
	
	private Integer roleId;
	private String roleName;
	private String roleAlias;
	private Integer roleStatus;
	private String roleDesc;
	private String createTime;
	private String modifyTime;
	private Integer modifyOperatorId;
	
	private List<Resource> resourceList;
	
	public Role() {
		super();
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleAlias() {
		return roleAlias;
	}
	public void setRoleAlias(String roleAlias) {
		this.roleAlias = roleAlias;
	}
	public Integer getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getModifyOperatorId() {
		return modifyOperatorId;
	}
	public void setModifyOperatorId(Integer modifyOperatorId) {
		this.modifyOperatorId = modifyOperatorId;
	}
	
	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleAlias=" + roleAlias + ", roleStatus="
				+ roleStatus + ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", modifyOperatorId=" + modifyOperatorId + "]";
	}
	
}
