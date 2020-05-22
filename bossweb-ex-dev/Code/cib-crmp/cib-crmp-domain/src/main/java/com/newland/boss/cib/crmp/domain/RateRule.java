package com.newland.boss.cib.crmp.domain;

public class RateRule {
	private Integer rateItemId;
	private String rateRuleName;
	private Integer cdrType;
	private Integer rateType;
	private Integer priority;
	private Long fee;
	private Long otherFee;
	private Integer bandwidth;
	private String startTime;
	private String endTime;
	private Integer rateStatus;
	private String inureDate;
	private String expireDate;
	private String rateDesc;
	private Integer operatorId;
	private String createTime;
	private Integer scope;
	private Integer orgId;
	
	public RateRule() {
		super();
	}

	public Integer getRateItemId() {
		return rateItemId;
	}

	public void setRateItemId(Integer rateItemId) {
		this.rateItemId = rateItemId;
	}

	public String getRateRuleName() {
		return rateRuleName;
	}

	public void setRateRuleName(String rateRuleName) {
		this.rateRuleName = rateRuleName;
	}

	public Integer getCdrType() {
		return cdrType;
	}

	public void setCdrType(Integer cdrType) {
		this.cdrType = cdrType;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Long getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Long otherFee) {
		this.otherFee = otherFee;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
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

	public Integer getRateStatus() {
		return rateStatus;
	}

	public void setRateStatus(Integer rateStatus) {
		this.rateStatus = rateStatus;
	}

	public String getInureDate() {
		return inureDate;
	}

	public void setInureDate(String inureDate) {
		this.inureDate = inureDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getRateDesc() {
		return rateDesc;
	}

	public void setRateDesc(String rateDesc) {
		this.rateDesc = rateDesc;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Override
    public String toString() {
        return "RateRule [rateItemId=" + rateItemId + ", rateRuleName=" + rateRuleName + ", cdrType=" + cdrType
                + ", rateType=" + rateType + ", priority=" + priority + ", fee=" + fee + ", otherFee=" + otherFee
                + ", bandwidth=" + bandwidth + ", startTime=" + startTime + ", endTime=" + endTime + ", rateStatus="
                + rateStatus + ", inureDate=" + inureDate + ", expireDate=" + expireDate + ", rateDesc=" + rateDesc
                + ", operatorId=" + operatorId + ", createTime=" + createTime + ", scope=" + scope + "]";
    }

	
	
}
