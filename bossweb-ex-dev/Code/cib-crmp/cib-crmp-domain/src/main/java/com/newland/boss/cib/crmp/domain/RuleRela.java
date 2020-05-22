/**
 * 
 */
package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * @author ylc
 *
 * 2018-05-8
 */
public class RuleRela {

	// 分配关联ID
	private Integer apportionRelaId;
	// 对象类型 1：单位 2：员工
	private Integer objectType;
	// 对象ID 当对象类型为1(单位)时，填单位ID;当对象类型为2(员工)时，填员工ID
	private Integer objectId;
	// 组织名称
	private String  orgName;
	// 摊分规则ID
	private Integer apportionItemId;
	// 分配规则名称
	private String apportionRuleName;
	// 启动状态
	private Integer status;
	// 生效日期
	private Date inureDate;
	// 失效日期
	private Date expireDate;
	// 操作工号
	private Integer operatorId;
	// 创建时间
	private Date createDate;

	public RuleRela() {
		super();
	}

	public Integer getApportionRelaId() {
		return apportionRelaId;
	}

	public void setApportionRelaId(Integer apportionRelaId) {
		this.apportionRelaId = apportionRelaId;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return "RuleRela [apportionRelaId=" + apportionRelaId + ", objectType=" + objectType + ", objectId=" + objectId
				+ ", orgName=" + orgName + ", apportionItemId=" + apportionItemId + ", apportionRuleName="
				+ apportionRuleName + ", status=" + status + ", inureDate=" + inureDate + ", expireDate=" + expireDate
				+ ", operatorId=" + operatorId + ", createDate=" + createDate + "]";
	}

	

}
