package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class SpecialInsFee {
	
	private String specialNumber;  // 特殊号码
	private Date startTime;  // 开始时间
	private Date endTime;  // 结束时间
	private Integer joinCount;  // 方数
	private Double fee;  // 费用
	private Date createTime;  // 创建时间
	private Integer operateId;  // 工单编号
	
	public SpecialInsFee() {
		super();
	}

	public String getSpecialNumber() {
		return specialNumber;
	}

	public void setSpecialNumber(String specialNumber) {
		this.specialNumber = specialNumber;
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

	public Integer getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	@Override
	public String toString() {
		return "SpecialInsFee [specialNumber=" + specialNumber + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", joinCount=" + joinCount + ", fee=" + fee + ", createTime=" + createTime + ", operateId="
				+ operateId + "]";
	}
	
}
