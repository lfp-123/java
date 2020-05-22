package com.newland.boss.cib.crmp.domain;

import java.io.File;

public class RecalcCdrTaskBean {
	
	private int recalcTaskId;
	private int recalcType; 
	private int billMonth;
	private int cdrType;
	private int recalcProgress;
	private int status;
	private int operatorId;
	private String createTime;
	private String startTime;
	private String endTime;
	private String description;
	private File tmpFile;
	
	public int getRecalcTaskId() {
		return recalcTaskId;
	}
	public void setRecalcTaskId(int recalcTaskId) {
		this.recalcTaskId = recalcTaskId;
	}
	public int getRecalcType() {
		return recalcType;
	}
	public void setRecalcType(int recalcType) {
		this.recalcType = recalcType;
	}
	public int getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}
	public int getCdrType() {
		return cdrType;
	}
	public void setCdrType(int cdrType) {
		this.cdrType = cdrType;
	}
	public int getRecalcProgress() {
		return recalcProgress;
	}
	public void setRecalcProgress(int recalcProgress) {
		this.recalcProgress = recalcProgress;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public File getTmpFile() {
		return tmpFile;
	}
	public void setTmpFile(File tmpFile) {
		this.tmpFile = tmpFile;
	}
	
	@Override
	public String toString() {
		return "RecalcCdrTaskBean [recalcTaskId=" + recalcTaskId + ", recalcType=" + recalcType + ", billMonth="
				+ billMonth + ", cdrType=" + cdrType + ", recalcProgress=" + recalcProgress + ", status=" + status
				+ ", operatorId=" + operatorId + ", createTime=" + createTime + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", description=" + description + ", tmpFile=" + tmpFile + "]";
	}
	
	
}
