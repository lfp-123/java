package com.newland.boss.kpi.admin.model;

import java.util.List;

public class Resource {
	
	private Integer resourceId;
	private Integer superResourceId;
	private String resourceName;
	private String resourceUrl;
	private Integer resourceType;
	private String resourceOrder;
	private String createTime;
	private String modifyTime;
	private Integer modifyOperatorId;
	private List<Resource> childResourceList;
	
	public Resource() {
		super();
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
	public Integer getResourceType() {
		return resourceType;
	}
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
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
	
	public List<Resource> getChildResourceList() {
		return childResourceList;
	}

	public void setChildResourceList(List<Resource> childResourceList) {
		this.childResourceList = childResourceList;
	}

	public String getResourceOrder() {
		return resourceOrder;
	}

	public void setResourceOrder(String resourceOrder) {
		this.resourceOrder = resourceOrder;
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", superResourceId=" + superResourceId + ", resourceName="
				+ resourceName + ", resourceUrl=" + resourceUrl + ", resourceType=" + resourceType + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId + "]";
	}
	
}
