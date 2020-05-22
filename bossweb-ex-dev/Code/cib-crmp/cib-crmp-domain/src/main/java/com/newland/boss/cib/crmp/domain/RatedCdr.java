package com.newland.boss.cib.crmp.domain;

import java.io.Serializable;
import java.util.Date;

public class RatedCdr implements Serializable{

	private static final long serialVersionUID = -6494308351486291267L;
	private String cdrId;
	private int orgId;
	private String orgName;
	private int operatorId;
	private int billMonth;
	private int cdrType;
	private int region;
	private String callNumber;
	private String calledNumber;
	private Date startTime;
	private Date endTime;
	private long fee;
	private long count;
	private String fileName;
	private Date guidingTime;
	private Date firstRatingTime;
	private Date createTime;
	private long holdingTime;//通话时长
	private String operatorName;
	private String userId;
	private String callType;
	private String callTypeName;
	private String totalFee;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getCdrId() {
		return cdrId;
	}
	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
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
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	public String getCalledNumber() {
		return calledNumber;
	}
	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getGuidingTime() {
		return guidingTime;
	}
	public void setGuidingTime(Date guidingTime) {
		this.guidingTime = guidingTime;
	}
	public Date getFirstRatingTime() {
		return firstRatingTime;
	}
	public void setFirstRatingTime(Date firstRatingTime) {
		this.firstRatingTime = firstRatingTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime(long holdingTime) {
		this.holdingTime = holdingTime;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getCallTypeName() {
		return callTypeName;
	}
	public void setCallTypeName(String callTypeName) {
		this.callTypeName = callTypeName;
	}
	
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	
	@Override
	public String toString() {
		return "RatedCdr [cdrId=" + cdrId + ", orgId=" + orgId + ", orgName=" + orgName + ", operatorId=" + operatorId
				+ ", billMonth=" + billMonth + ", cdrType=" + cdrType + ", region=" + region + ", callNumber="
				+ callNumber + ", calledNumber=" + calledNumber + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", fee=" + fee + ", count=" + count + ", fileName=" + fileName + ", guidingTime=" + guidingTime
				+ ", firstRatingTime=" + firstRatingTime + ", createTime=" + createTime + ", holdingTime=" + holdingTime
				+ ", operatorName=" + operatorName + ", userId=" + userId + ", callType=" + callType + ", callTypeName="
				+ callTypeName + ", totalFee=" + totalFee + "]";
	}
	
}
