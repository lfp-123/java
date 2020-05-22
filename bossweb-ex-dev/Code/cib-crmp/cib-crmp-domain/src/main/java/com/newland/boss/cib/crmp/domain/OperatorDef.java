package com.newland.boss.cib.crmp.domain;

/**
 * 描述:
 * 用户资料以及机构信息
 * 只查需要使用的信息。别的不查。
 * @author weixc
 * create 2018-06-13 15:26
 */
public class OperatorDef {

    private Integer operatorId = 0;
    private String operatorName;
    private Integer operatorStatus;
    private String directNumber;
    private String seatNumber;
    private String mobileNumber;
    private Integer orgId = 0;
    private String loginName = "0";

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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getOperatorStatus() {
        return operatorStatus;
    }

    public void setOperatorStatus(Integer operatorStatus) {
        this.operatorStatus = operatorStatus;
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}

