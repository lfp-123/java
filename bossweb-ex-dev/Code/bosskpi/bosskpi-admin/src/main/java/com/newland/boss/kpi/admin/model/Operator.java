package com.newland.boss.kpi.admin.model;

import java.util.List;

import com.newland.boss.kpi.entity.Organization;

public class Operator {
	
	private Integer operatorId;
	private String operatorName;
	private String password;
	private Integer operatorStatus;
	private Integer operatorLevel;
	private String directNumber;
	private String seatNumber;
	private String mobileNumber;
	private String createTime;
	private String modifyTime;  //修改时间
	private Integer modifyOperatorId;  //修改的操作员工号 
	private Integer orgId;
	private String loginName;
	private List<Organization> orgList;
	private List<Role> roleList;
	
	public Operator() {
		super();
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOperatorStatus() {
		return operatorStatus;
	}

	public void setOperatorStatus(Integer operatorStatus) {
		this.operatorStatus = operatorStatus;
	}

	public Integer getOperatorLevel() {
		return operatorLevel;
	}

	public void setOperatorLevel(Integer operatorLevel) {
		this.operatorLevel = operatorLevel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyOperatorId() {
		return modifyOperatorId;
	}

	public void setModifyOperatorId(Integer modifyOperatorId) {
		this.modifyOperatorId = modifyOperatorId;
	}
	
	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}

	public String getDirectNumber() {
		return directNumber;
	}

	public void setDirectNumber(String directNumber) {
		this.directNumber = directNumber;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return "Operator [operatorId=" + operatorId + ", operatorName=" + operatorName + ", password=" + password
				+ ", operatorStatus=" + operatorStatus + ", operatorLevel=" + operatorLevel + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId + "]";
	}
	
}
