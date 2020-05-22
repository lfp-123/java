/**
 * 
 */
package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * @author ylc
 *
 * 2018-05-11
 */
public class SettleApportionRule {
	
	// 摊分规则ID
	private Integer apportionItemId;
	// 摊分规则名称
	private String apportionRuleName;
	// 话单类型
	private Integer cdrType;
	// 摊分比例
	private Double ratio;
	// 生效日期
	private Date inureDate;
	// 失效日期
	private Date expireDate;
	// 规则状态
	private Integer status;
	// 备注说明(可为空)
	private String apportionDesc;
	// 操作工号
	private Integer operatorId;
	// 创建时间
	private Date createTime;
	// 规则使用范围
	private Integer scope;

	public SettleApportionRule() {
		super();
	}

	public Integer getApportionItemId() {
		return apportionItemId;
	}

	public void setApportionItemId(Integer apportionItemId) {
		this.apportionItemId = apportionItemId;
	}

	public String getApportionRuleName() {
		return apportionRuleName;
	}

	public void setApportionRuleName(String apportionRuleName) {
		this.apportionRuleName = apportionRuleName;
	}

	public Integer getCdrType() {
		return cdrType;
	}

	public void setCdrType(Integer cdrType) {
		this.cdrType = cdrType;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
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

	public String getApportionDesc() {
		return apportionDesc;
	}

	public void setApportionDesc(String apportionDesc) {
		this.apportionDesc = apportionDesc;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "SettleApportionRule [apportionItemId=" + apportionItemId + ", apportionRuleName=" + apportionRuleName
				+ ", cdrType=" + cdrType + ", ratio=" + ratio + ", inureDate=" + inureDate + ", expireDate="
				+ expireDate + ", status=" + status + ", apportionDesc=" + apportionDesc + ", operatorId=" + operatorId
				+ ", createTime=" + createTime + ", scope=" + scope + "]";
	}



}
