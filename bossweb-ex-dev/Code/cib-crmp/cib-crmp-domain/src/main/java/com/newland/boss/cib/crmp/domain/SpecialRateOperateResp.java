package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class SpecialRateOperateResp {
	
	private Integer operateId;
	private String fileName;
	private Integer status;
	private String operatorName;
	private Date createTime;
	private String operateTitle;
	
	public SpecialRateOperateResp() {
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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getOperateTitle() {
		return operateTitle;
	}

	public void setOperateTitle(String operateTitle) {
		this.operateTitle = operateTitle;
	}
	
}
