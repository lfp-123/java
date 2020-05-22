package com.newland.boss.kpi.entity;

public class ResourceRole {
	
	private String resourceUrl;
	private String roleAlias;
		
	public ResourceRole() {
		super();
	}
	

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getRoleAlias() {
		return roleAlias;
	}

	public void setRoleAlias(String roleAlias) {
		this.roleAlias = roleAlias;
	}


	@Override
	public String toString() {
		return "ResourceRole [resourceUrl=" + resourceUrl + ", roleAlias=" + roleAlias + "]";
	}
	
}
