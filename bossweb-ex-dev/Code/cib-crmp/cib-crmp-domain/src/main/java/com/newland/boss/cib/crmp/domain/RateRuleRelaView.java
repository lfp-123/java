package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class RateRuleRelaView {

	private Integer rateRuleRelaId;
	private Integer objectType;
	private Integer objectId;
	private Integer operatorId;	
	private String operatorName;
	private Integer orgId;
	private String orgName;
	private Integer rateItemId;
    private Integer cdrType;
    private Integer rateType;
    private Integer priority;
	private String rateRuleName;
	private Date inureDate;
	private Date expireDate;
	private Integer status;
	private Integer modifyOperator;
	private Date createDate;
	
	
    public RateRuleRelaView() {
		super();
	}
	public Integer getRateRuleRelaId() {
		return rateRuleRelaId;
	}
	public void setRateRuleRelaId(Integer rateRuleRelaId) {
		this.rateRuleRelaId = rateRuleRelaId;
	}
	public Integer getObjectType() {
		return objectType;
	}
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Integer getRateItemId() {
		return rateItemId;
	}
	public void setRateItemId(Integer rateItemId) {
		this.rateItemId = rateItemId;
	}
	public Date getInureDate() {
		return inureDate;
	}
	public void setInureDate(Date inureDate) {
		this.inureDate = inureDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRateRuleName() {
		return rateRuleName;
	}
	public void setRateRuleName(String rateRuleName) {
		this.rateRuleName = rateRuleName;
	}
	public Integer getModifyOperator() {
		return modifyOperator;
	}
	public void setModifyOperator(Integer modifyOperator) {
		this.modifyOperator = modifyOperator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
    
    @Override
    public String toString() {
        return "RateRuleRelaView [rateRuleRelaId=" + rateRuleRelaId + ", objectType=" + objectType + ", objectId="
                + objectId + ", operatorId=" + operatorId + ", operatorName=" + operatorName + ", orgId=" + orgId
                + ", orgName=" + orgName + ", rateItemId=" + rateItemId + ", cdrType=" + cdrType + ", rateType="
                + rateType + ", priority=" + priority + ", rateRuleName=" + rateRuleName + ", inureDate=" + inureDate
                + ", expireDate=" + expireDate + ", status=" + status + ", modifyOperator=" + modifyOperator
                + ", createDate=" + createDate + "]";
    }
    
}
