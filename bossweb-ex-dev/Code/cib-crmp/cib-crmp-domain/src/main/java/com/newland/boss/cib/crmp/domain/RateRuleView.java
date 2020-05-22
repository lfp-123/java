package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: RateRuleView
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 资费规则分配关系
 * @date 2018/7/19
 */
public class RateRuleView {
    private Integer rateRuleRelaId;
    private Integer objectId;
    private Integer objectType;
    private Integer cdrType;
    private Integer rateType;
    private Integer priority;
    private Long fee;
    private Integer billMonth;

    private Integer operatorId;
    private Integer orgId;
    private Long otherFee;
    private Date inureDate;
    private Date expireDate;

    /**
     * Getter for property 'rateRuleRelaId'.
     *
     * @return Value for property 'rateRuleRelaId'.
     */
    public Integer getRateRuleRelaId() {
        return rateRuleRelaId;
    }

    /**
     * Setter for property 'rateRuleRelaId'.
     *
     * @param rateRuleRelaId Value to set for property 'rateRuleRelaId'.
     */
    public void setRateRuleRelaId(Integer rateRuleRelaId) {
        this.rateRuleRelaId = rateRuleRelaId;
    }

    /**
     * Getter for property 'objectId'.
     *
     * @return Value for property 'objectId'.
     */
    public Integer getObjectId() {
        return objectId;
    }

    /**
     * Setter for property 'objectId'.
     *
     * @param objectId Value to set for property 'objectId'.
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    /**
     * Getter for property 'objectType'.
     *
     * @return Value for property 'objectType'.
     */
    public Integer getObjectType() {
        return objectType;
    }

    /**
     * Setter for property 'objectType'.
     *
     * @param objectType Value to set for property 'objectType'.
     */
    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    /**
     * Getter for property 'cdrType'.
     *
     * @return Value for property 'cdrType'.
     */
    public Integer getCdrType() {
        return cdrType;
    }

    /**
     * Setter for property 'cdrType'.
     *
     * @param cdrType Value to set for property 'cdrType'.
     */
    public void setCdrType(Integer cdrType) {
        this.cdrType = cdrType;
    }

    /**
     * Getter for property 'rateType'.
     *
     * @return Value for property 'rateType'.
     */
    public Integer getRateType() {
        return rateType;
    }

    /**
     * Setter for property 'rateType'.
     *
     * @param rateType Value to set for property 'rateType'.
     */
    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    /**
     * Getter for property 'priority'.
     *
     * @return Value for property 'priority'.
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Setter for property 'priority'.
     *
     * @param priority Value to set for property 'priority'.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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
     * Getter for property 'otherFee'.
     *
     * @return Value for property 'otherFee'.
     */
    public Long getOtherFee() {
        return otherFee;
    }

    /**
     * Setter for property 'otherFee'.
     *
     * @param otherFee Value to set for property 'otherFee'.
     */
    public void setOtherFee(Long otherFee) {
        this.otherFee = otherFee;
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
}
