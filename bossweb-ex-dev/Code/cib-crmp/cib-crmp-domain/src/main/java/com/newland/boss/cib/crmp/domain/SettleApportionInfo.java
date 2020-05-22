package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2017,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: SettleApportionInfo
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 结算摊分表
 * @date 2018/5/14
 */
public class SettleApportionInfo {
    private Integer cdrType;
    private Integer orgId;
    private Integer billMonth;
    private Integer apportionItemId;
    private Long totalAmount;
    private Long totalFee;
    private Date createTime;

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
     * Getter for property 'totalAmount'.
     *
     * @return Value for property 'totalAmount'.
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     * Setter for property 'totalAmount'.
     *
     * @param totalAmount Value to set for property 'totalAmount'.
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
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
}
