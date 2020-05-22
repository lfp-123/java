package com.newland.boss.kpi.admin.model;

import java.util.Date;

public class OperatorAuth {
	
	private Integer operatorId;
	private Integer resourceId;
	private Integer status;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyOperatorId;
	
	public OperatorAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		return "OperatorAuth [operatorId=" + operatorId + ", resourceId=" + resourceId + ", status=" + status
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId
				+ "]";
	}
	
	
}
