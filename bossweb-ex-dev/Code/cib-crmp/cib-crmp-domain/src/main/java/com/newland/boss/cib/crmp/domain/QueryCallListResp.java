package com.newland.boss.cib.crmp.domain;

public class QueryCallListResp {
	
	private String resourceType;//资源类型
	private String orgId;//单位id
	private String orgName;//单位名称
	private String operatorId;//员工号码
	private String callNumber;
	private String calledNumber;
	private String startTime;
	private String endTime;
	private String holdingTime;
	private String region;//地区
	private String ip;
	private String terminalName;//终端名
	private String conferenceNumber;//会议号
	private String netResource;//专线资源
	private String partCount;
	private String billMonth;
	private String fee;
	private String otherFee;
	private String count;
	private String operatorName;
	private String callType;
	private String callTypeId;
	private String confUuid;
	private String cdrId;
	private String feeType;
	public String getCdrId() {
		return cdrId;
	}
	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}
	public String getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	public String getConfUuid() {
		return confUuid;
	}
	public void setConfUuid(String confUuid) {
		this.confUuid = confUuid;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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
	public String getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime(String holdingTime) {
		this.holdingTime = holdingTime;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	public String getConferenceNumber() {
		return conferenceNumber;
	}
	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}
	public String getNetResource() {
		return netResource;
	}
	public void setNetResource(String netResource) {
		this.netResource = netResource;
	}
	public String getPartCount() {
		return partCount;
	}
	public void setPartCount(String partCount) {
		this.partCount = partCount;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getCallTypeId() {
		return callTypeId;
	}
	public void setCallTypeId(String callTypeId) {
		this.callTypeId = callTypeId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
}
