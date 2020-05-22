package com.newland.boss.kpi.entity;

import java.util.Date;

public class OperateLog {

	private String operateId;
	private Integer operatorId;
	private Integer operateModule;
	private Integer operateType;
	private Integer operateMonth;
	private String operateDesc;
	private Date createTime;

	public OperateLog() {
		super();
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getOperateModule() {
		return operateModule;
	}

	public void setOperateModule(Integer operateModule) {
		this.operateModule = operateModule;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOperateMonth() {
		return operateMonth;
	}

	public void setOperateMonth(Integer operateMonth) {
		this.operateMonth = operateMonth;
	}

	@Override
	public String toString() {
		return "OperateLog [operateId=" + operateId + ", operatorId=" + operatorId + ", operateModule=" + operateModule
				+ ", operateType=" + operateType + ", operateMonth=" + operateMonth + ", operateDesc=" + operateDesc
				+ ", createTime=" + createTime + "]";
	}

}