package com.newland.boss.cib.crmp.web.model;

public class QueryRecalculationReq {

	private String cdrType;
	private String recalcType;// 重算类型
	private String billMonth;
	private String beginDate;
	private String endDate; //beginDate~endDate 查询周期
	private int operatorId;
	private String addCdrType;
	private String addRecalcType;// 重算类型
	private String addBillMonth;
	private String addDesc;
	public String getCdrType() {
		return cdrType;
	}
	public void setCdrType(String cdrType) {
		this.cdrType = cdrType;
	}
	public String getRecalcType() {
		return recalcType;
	}
	public void setRecalcType(String recalcType) {
		this.recalcType = recalcType;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
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
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public String getAddCdrType() {
		return addCdrType;
	}
	public void setAddCdrType(String addCdrType) {
		this.addCdrType = addCdrType;
	}
	public String getAddRecalcType() {
		return addRecalcType;
	}
	public void setAddRecalcType(String addRecalcType) {
		this.addRecalcType = addRecalcType;
	}
	public String getAddBillMonth() {
		return addBillMonth;
	}
	public void setAddBillMonth(String addBillMonth) {
		this.addBillMonth = addBillMonth;
	}
	public String getAddDesc() {
		return addDesc;
	}
	public void setAddDesc(String addDesc) {
		this.addDesc = addDesc;
	}
}
