package com.newland.boss.cib.crmp.domain;

public class QueryFeeListReq {
	
	private String unitName;  // 单位名称
	private String userName;  // 用户名
	private String feeType;  // 资费类型
	private String feeTypeName;  // 资费类型名称
	private String beginDate;
	private String endDate;  // beginDate~endDate 查询周期
	private String topN;  // 费用使用前N
	private String thresholdValue;  // 阀值
	private Integer operatorLevel;  // 岗位级别
	private String notesId;   // 登录名
	private Integer orgId;  // 机构编号
	private Integer operatorId; // 操作员工号
	
	public QueryFeeListReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getFeeTypeName() {
		return feeTypeName;
	}
	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}
	public Integer getOperatorLevel() {
		return operatorLevel;
	}
	public void setOperatorLevel(Integer operatorLevel) {
		this.operatorLevel = operatorLevel;
	}
	public String getThresholdValue() {
		return thresholdValue;
	}
	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	public String getTopN() {
		return topN;
	}
	public void setTopN(String topN) {
		this.topN = topN;
	}
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
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
	public String getNotesId() {
		return notesId;
	}
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	@Override
	public String toString() {
		return "QueryFeeListReq [unitName=" + unitName + ", userName=" + userName + ", feeType=" + feeType
				+ ", feeTypeName=" + feeTypeName + ", beginDate=" + beginDate + ", endDate=" + endDate + ", topN="
				+ topN + ", thresholdValue=" + thresholdValue + ", operatorLevel=" + operatorLevel + ", notesId="
				+ notesId + ", orgId=" + orgId + ", operatorId=" + operatorId + "]";
	}

}
