package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class SpecialRateOperate {
	
	private Integer operateId;
	private String fileName;
	private Integer status;
	private Integer operatorId;
	private Date createTime;
	private String loginName;
	private String operateTitle;
	
	public SpecialRateOperate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getOperateTitle() {
		return operateTitle;
	}

	public void setOperateTitle(String operateTitle) {
		this.operateTitle = operateTitle;
	}

	@Override
	public String toString() {
		return "SpecialRateOperate [operateId=" + operateId + ", fileName=" + fileName + ", status=" + status
				+ ", operatorId=" + operatorId + ", createTime=" + createTime + ", loginName=" + loginName
				+ ", operateTitle=" + operateTitle + "]";
	}
	
}
