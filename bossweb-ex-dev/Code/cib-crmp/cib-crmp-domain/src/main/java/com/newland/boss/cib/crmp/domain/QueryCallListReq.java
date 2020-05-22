package com.newland.boss.cib.crmp.domain;

import java.util.List;

public class QueryCallListReq {
	
	private String resourceType;//资源类型
	private String orgName;//单位名称
	private String operatorId;//员工号码(用于查询)
	private String operatorName;
	private String callNumber;
	private String calledNumber;
	private String ip;
	private String beginDate;
	private String endDate; //beginDate~endDate 查询周期
	private Integer orgId; //单位Id
	private Integer operatorLevel; //岗位级别
	private Integer userOperatorId; //操作员员工号码
	private String userLoginName; //操作员notes名
	private String loginName;
	private String roomId;
	private List<String> unitName;
	private String billMonth;  // 月份
	private String callType;
	private String realCallTypes;
	private String[] callTypes;
	private String confUuid;
	private String cdrId;
	private Long fee;
	private Long holdingTime;
	private boolean addFlag;
	private String startTime;
	private String endTime;
	private String feeType;
	private Integer partCount;
	private String startBillMonth;
	private String endBillMonth;
	private String cdrType;
	
	public String getStartBillMonth() {
		return startBillMonth;
	}
	public void setStartBillMonth(String startBillMonth) {
		this.startBillMonth = startBillMonth;
	}
	public String getEndBillMonth() {
		return endBillMonth;
	}
	public void setEndBillMonth(String endBillMonth) {
		this.endBillMonth = endBillMonth;
	}
	public Integer getPartCount() {
		return partCount;
	}
	public void setPartCount(Integer partCount) {
		this.partCount = partCount;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getCdrId() {
		return cdrId;
	}
	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}
	public String getConfUuid() {
		return confUuid;
	}
	public void setConfUuid(String confUuid) {
		this.confUuid = confUuid;
	}
	public Integer getUserOperatorId() {
		return userOperatorId;
	}
	public void setUserOperatorId(Integer userOperatorId) {
		this.userOperatorId = userOperatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getOperatorLevel() {
		return operatorLevel;
	}
	public void setOperatorLevel(Integer operatorLevel) {
		this.operatorLevel = operatorLevel;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public List<String> getUnitName() {
		return unitName;
	}
	public void setUnitName(List<String> unitName) {
		this.unitName = unitName;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String[] getCallTypes() {
		return callTypes;
	}
	public void setCallTypes(String[] strings) {
		this.callTypes = strings;
	}
	public String getRealCallTypes() {
		return realCallTypes;
	}
	public void setRealCallTypes(String realCallTypes) {
		this.realCallTypes = realCallTypes;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime(Long holdingTime) {
		this.holdingTime = holdingTime;
	}
	public boolean isAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
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
	
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	public String getCdrType() {
		return cdrType;
	}
	public void setCdrType(String cdrType) {
		this.cdrType = cdrType;
	}
	
}
