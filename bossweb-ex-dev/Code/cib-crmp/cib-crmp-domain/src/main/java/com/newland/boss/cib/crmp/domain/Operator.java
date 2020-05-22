package com.newland.boss.cib.crmp.domain;

import java.util.Date;

public class Operator {

    private Integer operatorId;
    private String loginName;
    private String operatorName;
    private String password;
    private Integer operatorStatus;
    private Integer operatorLevel;

    private Date createTime;
    private Date modifyTime;  //修改时间
    private Integer modifyOperatorId;  //修改的操作员工号

    private String directNumber;
    private String seatNumber;
    private String mobileNumber;
    private Integer isSync;

    private Organization organization;


    public Operator() {
        super();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }


    /**
     * Getter for property 'directNumber'.
     *
     * @return Value for property 'directNumber'.
     */
    public String getDirectNumber() {
        return directNumber;
    }

    /**
     * Setter for property 'directNumber'.
     *
     * @param directNumber Value to set for property 'directNumber'.
     */
    public void setDirectNumber(String directNumber) {
        this.directNumber = directNumber;
    }

    /**
     * Getter for property 'seatNumber'.
     *
     * @return Value for property 'seatNumber'.
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * Setter for property 'seatNumber'.
     *
     * @param seatNumber Value to set for property 'seatNumber'.
     */
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Getter for property 'mobileNumber'.
     *
     * @return Value for property 'mobileNumber'.
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Setter for property 'mobileNumber'.
     *
     * @param mobileNumber Value to set for property 'mobileNumber'.
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Getter for property 'isSync'.
     *
     * @return Value for property 'isSync'.
     */
    public Integer getIsSync() {
        return isSync;
    }

    /**
     * Setter for property 'isSync'.
     *
     * @param isSync Value to set for property 'isSync'.
     */
    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    /**
     * Getter for property 'organization'.
     *
     * @return Value for property 'organization'.
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Setter for property 'organization'.
     *
     * @param organization Value to set for property 'organization'.
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Operator [operatorId=" + operatorId + ", operatorName=" + operatorName + ", password=" + password
                + ", operatorStatus=" + operatorStatus + ", operatorLevel=" + operatorLevel + ", createTime="
                + createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId + "]";
    }

}
