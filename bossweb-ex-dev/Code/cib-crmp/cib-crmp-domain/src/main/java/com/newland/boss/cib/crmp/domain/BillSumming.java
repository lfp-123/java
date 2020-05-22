package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2017,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: billSummingUsrDay
 * @Package com.newland.boss.cib.crmp.model
 * @Description:
 * @date 2018/5/10
 */
public class BillSumming {
    private Integer cdrType;
    private Integer orgId;
    private Integer operatorId;
    private Integer billMonth;
    private Integer billDay;
    private Long totalNumber;
    private Long totalMinutes;
    private Long totalFee;
    private Date createTime;
    private Integer feeType;
    private Double ratio;
    private Integer apportionItemId;
    private Integer directNumber;

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
     * Getter for property 'billDay'.
     *
     * @return Value for property 'billDay'.
     */
    public Integer getBillDay() {
        return billDay;
    }

    /**
     * Setter for property 'billDay'.
     *
     * @param billDay Value to set for property 'billDay'.
     */
    public void setBillDay(Integer billDay) {
        this.billDay = billDay;
    }

    /**
     * Getter for property 'totalNumber'.
     *
     * @return Value for property 'totalNumber'.
     */
    public Long getTotalNumber() {
        return totalNumber;
    }

    /**
     * Setter for property 'totalNumber'.
     *
     * @param totalNumber Value to set for property 'totalNumber'.
     */
    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * Getter for property 'totalMinutes'.
     *
     * @return Value for property 'totalMinutes'.
     */
    public Long getTotalMinutes() {
        return totalMinutes;
    }

    /**
     * Setter for property 'totalMinutes'.
     *
     * @param totalMinutes Value to set for property 'totalMinutes'.
     */
    public void setTotalMinutes(Long totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    /**
     * Getter for property 'totalFee'.
     *
     * @return Value for property 'totalFee'.
     */
    public Long getTotalFee() {
        return totalFee;
    }

    /**
     * Setter for property 'totalFee'.
     *
     * @param totalFee Value to set for property 'totalFee'.
     */
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
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
     * Getter for property 'ratio'.
     *
     * @return Value for property 'ratio'.
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * Setter for property 'ratio'.
     *
     * @param ratio Value to set for property 'ratio'.
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    /**
     * Getter for property 'apportionItemId'.
     *
     * @return Value for property 'apportionItemId'.
     */
    public Integer getApportionItemId() {
        return apportionItemId;
    }

    /**
     * Setter for property 'apportionItemId'.
     *
     * @param apportionItemId Value to set for property 'apportionItemId'.
     */
    public void setApportionItemId(Integer apportionItemId) {
        this.apportionItemId = apportionItemId;
    }

    /**
     * Getter for property 'directNumber'.
     *
     * @return Value for property 'directNumber'.
     */
    public Integer getDirectNumber() {
        return directNumber;
    }

    /**
     * Setter for property 'directNumber'.
     *
     * @param directNumber Value to set for property 'directNumber'.
     */
    public void setDirectNumber(Integer directNumber) {
        this.directNumber = directNumber;
    }
}
