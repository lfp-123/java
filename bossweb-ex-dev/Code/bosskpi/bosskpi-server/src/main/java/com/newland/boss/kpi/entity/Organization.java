package com.newland.boss.kpi.entity;

import java.util.List;

public class Organization {
	
	private Integer orgId;
	private Integer superOrgId;
	private String orgName;
	private Integer orgStatus;
	private String orgDesc;
	private String createTime;
	private String modifyTime;
	private Integer modifyOperatorId;
	private String orgNameFull;
	private Integer isSync;
	private List<Organization> childOrgList;
	
	public List<Organization> getChildOrgList() {
		return childOrgList;
	}

	public void setChildOrgList(List<Organization> childOrgList) {
		this.childOrgList = childOrgList;
	}

	public Organization() {
		super();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getSuperOrgId() {
		return superOrgId;
	}

	public void setSuperOrgId(Integer superOrgId) {
		this.superOrgId = superOrgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
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

	public String getOrgNameFull() {
		return orgNameFull;
	}

	public void setOrgNameFull(String orgNameFull) {
		this.orgNameFull = orgNameFull;
	}

	public Integer getIsSync() {
		return isSync;
	}

	public void setIsSync(Integer isSync) {
		this.isSync = isSync;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", superOrgId=" + superOrgId + ", orgName=" + orgName + ", orgStatus="
				+ orgStatus + ", orgDesc=" + orgDesc + ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", modifyOperatorId=" + modifyOperatorId + "]";
	}
	
}
