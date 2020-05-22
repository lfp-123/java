package com.newland.boss.cib.crmp.domain;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: RateRuleSpecial
 * @Package com.newland.boss.cib.crmp.model
 * @Description:
 * @date 2018/8/6
 */
public class RateRuleSpecial {
    private Integer specialNumber;
    private Integer startTime;
    private Integer endTime;
    private Integer joinCount;
    private Integer fee;
    private Integer createTime;
    private Integer operateId;

    /**
     * Getter for property 'specialNumber'.
     *
     * @return Value for property 'specialNumber'.
     */
    public Integer getSpecialNumber() {
        return specialNumber;
    }

    /**
     * Setter for property 'specialNumber'.
     *
     * @param specialNumber Value to set for property 'specialNumber'.
     */
    public void setSpecialNumber(Integer specialNumber) {
        this.specialNumber = specialNumber;
    }

    /**
     * Getter for property 'startTime'.
     *
     * @return Value for property 'startTime'.
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * Setter for property 'startTime'.
     *
     * @param startTime Value to set for property 'startTime'.
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for property 'endTime'.
     *
     * @return Value for property 'endTime'.
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * Setter for property 'endTime'.
     *
     * @param endTime Value to set for property 'endTime'.
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for property 'joinCount'.
     *
     * @return Value for property 'joinCount'.
     */
    public Integer getJoinCount() {
        return joinCount;
    }

    /**
     * Setter for property 'joinCount'.
     *
     * @param joinCount Value to set for property 'joinCount'.
     */
    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    /**
     * Getter for property 'fee'.
     *
     * @return Value for property 'fee'.
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * Setter for property 'fee'.
     *
     * @param fee Value to set for property 'fee'.
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * Getter for property 'createTime'.
     *
     * @return Value for property 'createTime'.
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * Setter for property 'createTime'.
     *
     * @param createTime Value to set for property 'createTime'.
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * Getter for property 'operateId'.
     *
     * @return Value for property 'operateId'.
     */
    public Integer getOperateId() {
        return operateId;
    }

    /**
     * Setter for property 'operateId'.
     *
     * @param operateId Value to set for property 'operateId'.
     */
    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }
}
