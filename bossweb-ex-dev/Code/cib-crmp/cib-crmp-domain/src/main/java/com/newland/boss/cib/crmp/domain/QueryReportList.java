package com.newland.boss.cib.crmp.domain;

public class QueryReportList {

	private String feeTypeName;  // 资费类型名称
	private String userName;  // 用户名
	private String month;  // 归属月份
	private String notesId;  // notesId
	private String orgNameFull;  // 机构全称
	private String serverUnit;  // 服务单位
	private Integer callTime;  // 通话次数
	private Float fee;  // 费用
	private Float rateFee;  // 服务单价
	private Integer totalMinutes;  // 服务时长
	private String importMonth;  // 导出月份
	private String feeType;  // 资费类型
	private String cdrType;  // 话单类型
	
	public QueryReportList() {
		super();
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

	public String getServerUnit() {
		return serverUnit;
	}

	public void setServerUnit(String serverUnit) {
		this.serverUnit = serverUnit;
	}

	public Integer getCallTime() {
		return callTime;
	}

	public void setCallTime(Integer callTime) {
		this.callTime = callTime;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public Float getRateFee() {
		return rateFee;
	}

	public void setRateFee(Float rateFee) {
		this.rateFee = rateFee;
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

	@Override
	public String toString() {
		return "QueryReportList [feeTypeName=" + feeTypeName + ", userName=" + userName + ", month=" + month
				+ ", notesId=" + notesId + ", orgNameFull=" + orgNameFull + ", serverUnit=" + serverUnit + ", callTime="
				+ callTime + ", fee=" + fee + ", rateFee=" + rateFee + ", totalMinutes=" + totalMinutes
				+ ", importMonth=" + importMonth + ", feeType=" + feeType + ", cdrType=" + cdrType + "]";
	}

}
