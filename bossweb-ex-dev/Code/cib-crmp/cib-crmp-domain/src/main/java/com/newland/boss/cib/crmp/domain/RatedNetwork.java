package com.newland.boss.cib.crmp.domain;

import java.io.Serializable;
import java.util.Date;

public class RatedNetwork implements Serializable{

	private static final long serialVersionUID = -575300677948208701L;
	private String cdrId;
	private int orgId;
	private String orgName;
	private int operatorId;
	private int billMonth;
	private int cdrType;
	private int region;
	private long callNumber;
	private long calledNumber;
	private Date startTime;
	private Date endTime;
	private long fee;
	private String fileName;
	private Date guidingTime;
	private Date firstRatingTime;
	private Date createTime;
	private long holdingTime;//通话时长
	private String netResource;
	private String feeType;
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
	public long getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(long callNumber) {
		this.callNumber = callNumber;
	}
	public long getCalledNumber() {
		return calledNumber;
	}
	public void setCalledNumber(long calledNumber) {
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
	public String getNetResource() {
		return netResource;
	}
	public void setNetResource(String netResource) {
		this.netResource = netResource;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
}
