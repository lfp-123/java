package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: OperatorOther
 * @Package com.newland.boss.cib.crmp.domain
 * @Description:
 * @date 2018/11/12
 */
public class OperatorOther {
    private Integer operatorId;
    private Date inureDate;
    private Date expireDate;
    private String directNumber;
    private String loginName;
    private Integer operatorStatus;

    /**
     * Getter for property 'operatorId'.
     *
     * @return Value for property 'operatorId'.
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * Setter for property 'operatorId'.
     *
     * @param operatorId Value to set for property 'operatorId'.
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * Getter for property 'inureDate'.
     *
     * @return Value for property 'inureDate'.
     */
    public Date getInureDate() {
        return inureDate;
    }

    /**
     * Setter for property 'inureDate'.
     *
     * @param inureDate Value to set for property 'inureDate'.
     */
    public void setInureDate(Date inureDate) {
        this.inureDate = inureDate;
    }

    /**
     * Getter for property 'expireDate'.
     *
     * @return Value for property 'expireDate'.
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Setter for property 'expireDate'.
     *
     * @param expireDate Value to set for property 'expireDate'.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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
     * Getter for property 'loginName'.
     *
     * @return Value for property 'loginName'.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Setter for property 'loginName'.
     *
     * @param loginName Value to set for property 'loginName'.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Getter for property 'operatorStatus'.
     *
     * @return Value for property 'operatorStatus'.
     */
    public Integer getOperatorStatus() {
        return operatorStatus;
    }

    /**
     * Setter for property 'operatorStatus'.
     *
     * @param operatorStatus Value to set for property 'operatorStatus'.
     */
    public void setOperatorStatus(Integer operatorStatus) {
        this.operatorStatus = operatorStatus;
    }
}
