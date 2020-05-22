package com.newland.boss.kpi.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	
	private Integer operatorId;
	private String operatorName;
	private String password;
	private boolean disabled;
	private Integer operatorStatus;
	private Integer operatorLevel;
	private Integer orgId;
	//t_role_auth
	private List<Role> roleList;
	private Map<String, List<Resource>> roleResourcesMap;
	private Integer errCount;
	private String lastErrTime;
	
	//t_operator_Auth
	private List<Resource> resourceList;

	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
 
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public Map<String, List<Resource>> getRoleResourcesMap() {
		
		if (this.roleResourcesMap == null) {

			this.roleResourcesMap = new HashMap<String, List<Resource>>();

			for (Role role : this.roleList) {
				roleResourcesMap.put(role.getRoleAlias(), role.getResourceList());
			}

		}
		return this.roleResourcesMap;
	}
	
	public void setRoleResourcesMap(Map<String, List<Resource>> roleResourcesMap) {
		this.roleResourcesMap = roleResourcesMap;
	}
	
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	
	public Integer getOperatorStatus() {
		return operatorStatus;
	}
	public void setOperatorStatus(Integer operatorStatus) {
		this.operatorStatus = operatorStatus;
	}
	public Integer getOperatorLevel() {
		return operatorLevel;
	}
	public void setOperatorLevel(Integer operatorLevel) {
		this.operatorLevel = operatorLevel;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getErrCount() {
		return errCount;
	}
	public void setErrCount(Integer errCount) {
		this.errCount = errCount;
	}
	public String getLastErrTime() {
		return lastErrTime;
	}
	public void setLastErrTime(String lastErrTime) {
		this.lastErrTime = lastErrTime;
	}
	//toString
	@Override
	public String toString() {
		return "User [operatorId=" + operatorId + ", operatorName=" + operatorName + ", password=" + password
				+ ", disabled=" + disabled + ", roleList=" + roleList + ", roleResourcesMap=" + roleResourcesMap
				+ ", resourceList=" + resourceList + "]";
	}
	
}