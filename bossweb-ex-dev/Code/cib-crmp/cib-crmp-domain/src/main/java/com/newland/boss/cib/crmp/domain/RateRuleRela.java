package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class RateRuleRela {

	private Integer rateRuleRelaId;
	private Integer cdrType;
	private Integer objectType;
	private Integer objectId;	
	private Integer rateItemId;
	private Date inureDate;
	private Date expireDate;
	private Integer status;
	private Integer operatorId;
	private Date createDate;
	
	public RateRuleRela() {
		super();
	}
	public Integer getRateRuleRelaId() {
		return rateRuleRelaId;
	}
	public void setRateRuleRelaId(Integer rateRuleRelaId) {
		this.rateRuleRelaId = rateRuleRelaId;
	}
	public Integer getCdrType() {
		return cdrType;
	}
	public void setCdrType(Integer cdrType) {
		this.cdrType = cdrType;
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
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "RateRuleRela [rateRuleRelaId=" + rateRuleRelaId + ", cdrType=" + cdrType + ", objectType=" + objectType
				+ ", objectId=" + objectId + ", rateItemId=" + rateItemId + ", inureDate=" + inureDate + ", expireDate="
				+ expireDate + ", status=" + status + ", operatorId=" + operatorId + ", createDate=" + createDate + "]";
	}
	
}
