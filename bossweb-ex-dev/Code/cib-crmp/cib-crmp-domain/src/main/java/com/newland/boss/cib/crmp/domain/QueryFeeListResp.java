package com.newland.boss.cib.crmp.domain;

import java.util.ArrayList;
import java.util.List;

public class QueryFeeListResp {
	
	private String unitName;  // 单位名称
	private String feeType;  // 资费类型
	private String feeTypeName;  // 资费类型名称
	private String userName;  // 用户名
	private String month;  // 归属月份
	private String notesId;  // notesId
	private String orgNameFull;  // 机构全称
	private String cdrType;  // 话单类型
	private String serverUnit;  // 服务单位
	private String callTime;  // 通话次数
	private String fee;  // 费用
	private String rateFee;  // 服务单价
	private String otherFee;  // 设备保障费
	private Integer totalMinutes;  // 服务时长
	private String importMonth;  // 导出时间
	private String operatorId;  // 操作员ID
	private Integer orgId;  // 机构ID
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeTypeName() {
		return feeTypeName;
	}
	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getNotesId() {
		return notesId;
	}
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
	
	public String getOrgNameFull() {
		return orgNameFull;
	}
	public void setOrgNameFull(String orgNameFull) {
		this.orgNameFull = orgNameFull;
	}
	
	public String getCdrType() {
		return cdrType;
	}
	public void setCdrType(String cdrType) {
		this.cdrType = cdrType;
	}
	public String getServerUnit() {
		return serverUnit;
	}
	public void setServerUnit(String serverUnit) {
		this.serverUnit = serverUnit;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getRateFee() {
		return rateFee;
	}
	public void setRateFee(String rateFee) {
		this.rateFee = rateFee;
	}
	public String getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	public Integer getTotalMinutes() {
		return totalMinutes;
	}
	public void setTotalMinutes(Integer totalMinutes) {
		this.totalMinutes = totalMinutes;
	}
	public String getImportMonth() {
		return importMonth;
	}
	public void setImportMonth(String importMonth) {
		this.importMonth = importMonth;
	}
	
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public static List<QueryFeeListResp> getData() {
		List<QueryFeeListResp> list = new ArrayList<QueryFeeListResp>();
		QueryFeeListResp bean = new QueryFeeListResp();
		list.add(bean);
		return list;
	}
	
	@Override
	public String toString() {
		return "QueryFeeListResp [unitName=" + unitName + ", feeType=" + feeType + ", feeTypeName=" + feeTypeName
				+ ", userName=" + userName + ", month=" + month + ", notesId=" + notesId + ", orgNameFull="
				+ orgNameFull + ", cdrType=" + cdrType + ", serverUnit=" + serverUnit + ", callTime=" + callTime
				+ ", fee=" + fee + ", rateFee=" + rateFee + ", otherFee=" + otherFee + ", totalMinutes=" + totalMinutes
				+ ", importMonth=" + importMonth + ", operatorId=" + operatorId + ", orgId=" + orgId + "]";
	}
	
}
