package com.newland.boss.kpi.entity;

import java.util.List;

public class Resource {

	private Integer operatorId;
	private Integer roleId;
	private Integer resourceId;
	private Integer superResourceId;
	private String resourceName;
	private String resourceUrl;
	private String resourceOrder;
	
	private List<Resource> childResourceList;

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getSuperResourceId() {
		return superResourceId;
	}

	public void setSuperResourceId(Integer superResourceId) {
		this.superResourceId = superResourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getResourceOrder() {
		return resourceOrder;
	}

	public void setResourceOrder(String resourceOrder) {
		this.resourceOrder = resourceOrder;
	}

	public List<Resource> getChildResourceList() {
		return childResourceList;
	}

	public void setChildResourceList(List<Resource> childResourceList) {
		this.childResourceList = childResourceList;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Resource [operatorId=" + operatorId + ", roleId=" + roleId + ", resourceId=" + resourceId
				+ ", superResourceId=" + superResourceId + ", resourceName=" + resourceName + ", resourceUrl="
				+ resourceUrl + ", resourceOrder=" + resourceOrder + ", childResourceList=" + childResourceList + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Resource)) {
			return false;
		}
		Resource res = (Resource) obj;
		return this.getResourceId().equals(res.getResourceId());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
