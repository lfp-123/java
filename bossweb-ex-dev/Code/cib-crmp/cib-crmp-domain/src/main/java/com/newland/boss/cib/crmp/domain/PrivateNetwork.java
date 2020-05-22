package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: PrivateNetwork
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 网络专线表
 * @date 2018/7/20
 */
public class PrivateNetwork {
    private Long cdrId;
    private Integer billMonth;
    private Integer orgId;
    private String userId;
    private Integer feeType;
    private Long fee;
    private Date createTime;
    private Integer operatorId;

    /**
     * Getter for property 'cdrId'.
     *
     * @return Value for property 'cdrId'.
     */
    public Long getCdrId() {
        return cdrId;
    }

    /**
     * Setter for property 'cdrId'.
     *
     * @param cdrId Value to set for property 'cdrId'.
     */
    public void setCdrId(Long cdrId) {
        this.cdrId = cdrId;
    }

    /**
     * Getter for property 'billMonth'.
     *
     * @return Value for property 'billMonth'.
     */
    public Integer getBillMonth() {
        return billMonth;
    }

    /**
     * Setter for property 'billMonth'.
     *
     * @param billMonth Value to set for property 'billMonth'.
     */
    public void setBillMonth(Integer billMonth) {
        this.billMonth = billMonth;
    }

    /**
     * Getter for property 'orgId'.
     *
     * @return Value for property 'orgId'.
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * Setter for property 'orgId'.
     *
     * @param orgId Value to set for property 'orgId'.
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * Getter for property 'userId'.
     *
     * @return Value for property 'userId'.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter for property 'userId'.
     *
     * @param userId Value to set for property 'userId'.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter for property 'feeType'.
     *
     * @return Value for property 'feeType'.
     */
    public Integer getFeeType() {
        return feeType;
    }

    /**
     * Setter for property 'feeType'.
     *
     * @param feeType Value to set for property 'feeType'.
     */
    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    /**
     * Getter for property 'fee'.
     *
     * @return Value for property 'fee'.
     */
    public Long getFee() {
        return fee;
    }

    /**
     * Setter for property 'fee'.
     *
     * @param fee Value to set for property 'fee'.
     */
    public void setFee(Long fee) {
        this.fee = fee;
    }

    /**
     * Getter for property 'createTime'.
     *
     * @return Value for property 'createTime'.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Setter for property 'createTime'.
     *
     * @param createTime Value to set for property 'createTime'.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
}
