package com.newland.boss.cib.crmp.web.model;

public class QueryRecalculationResp {
	
	private String billMonth;
	private String cdrType;
	private String recalcType;
	private String recalcTaskId;
	private String recalcProgress;
	private String status;
	private String operatorId;
	private String createTime;
	private String startTime;
	private String endTime;

	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCdrType() {
		return cdrType;
	}
	public void setCdrType(String cdrType) {
		this.cdrType = cdrType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRecalcType() {
		return recalcType;
	}
	public void setRecalcType(String recalcType) {
		this.recalcType = recalcType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRecalcTaskId() {
		return recalcTaskId;
	}
	public void setRecalcTaskId(String recalcTaskId) {
		this.recalcTaskId = recalcTaskId;
	}
	public String getRecalcProgress() {
		return recalcProgress;
	}
	public void setRecalcProgress(String recalcProgress) {
		this.recalcProgress = recalcProgress;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
