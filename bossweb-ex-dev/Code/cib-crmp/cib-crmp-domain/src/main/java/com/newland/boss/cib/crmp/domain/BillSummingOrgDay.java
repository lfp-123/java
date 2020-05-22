package com.newland.boss.cib.crmp.domain;

public class BillSummingOrgDay {
	
	private Integer cdrType;
	private Integer orgId;
	private Integer billMonth;
	private Integer billDay;
	private Long totalNumber;
	private Long totalMinutes;
	private Long totalFee;
	private String createTime;
	
	public Integer getCdrType() {
		return cdrType;
	}
	public void setCdrType(Integer cdrType) {
		this.cdrType = cdrType;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(Integer billMonth) {
		this.billMonth = billMonth;
	}
	public Integer getBillDay() {
		return billDay;
	}
	public void setBillDay(Integer billDay) {
		this.billDay = billDay;
	}
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public Long getTotalMinutes() {
		return totalMinutes;
	}
	public void setTotalMinutes(Long totalMinutes) {
		this.totalMinutes = totalMinutes;
	}
	public Long getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
