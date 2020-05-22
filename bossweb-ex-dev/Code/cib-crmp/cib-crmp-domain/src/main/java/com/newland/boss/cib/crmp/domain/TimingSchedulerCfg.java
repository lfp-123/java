package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: TimingSchedulerCfg
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 定时调度器配置表
 * @date 2018/5/16
 */
public class TimingSchedulerCfg {

    private Integer schedulerCfgId;
    private Integer schedulerType;
    private Integer cdrType;
    private String startTime;
    private Integer syncCycle;
    private String cycleValue;
    private Integer status;
    private Integer operatorId;
    private Date createTime;
    private Integer startupStatus;

    /**
     * Getter for property 'schedulerCfgId'.
     *
     * @return Value for property 'schedulerCfgId'.
     */
    public Integer getSchedulerCfgId() {
        return schedulerCfgId;
    }

    /**
     * Setter for property 'schedulerCfgId'.
     *
     * @param schedulerCfgId Value to set for property 'schedulerCfgId'.
     */
    public void setSchedulerCfgId(Integer schedulerCfgId) {
        this.schedulerCfgId = schedulerCfgId;
    }

    /**
     * Getter for property 'schedulerType'.
     *
     * @return Value for property 'schedulerType'.
     */
    public Integer getSchedulerType() {
        return schedulerType;
    }

    /**
     * Setter for property 'schedulerType'.
     *
     * @param schedulerType Value to set for property 'schedulerType'.
     */
    public void setSchedulerType(Integer schedulerType) {
        this.schedulerType = schedulerType;
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
     * Getter for property 'startTime'.
     *
     * @return Value for property 'startTime'.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Setter for property 'startTime'.
     *
     * @param startTime Value to set for property 'startTime'.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for property 'syncCycle'.
     *
     * @return Value for property 'syncCycle'.
     */
    public Integer getSyncCycle() {
        return syncCycle;
    }

    /**
     * Setter for property 'syncCycle'.
     *
     * @param syncCycle Value to set for property 'syncCycle'.
     */
    public void setSyncCycle(Integer syncCycle) {
        this.syncCycle = syncCycle;
    }

    /**
     * Getter for property 'cycleValue'.
     *
     * @return Value for property 'cycleValue'.
     */
    public String getCycleValue() {
        return cycleValue;
    }

    /**
     * Setter for property 'cycleValue'.
     *
     * @param cycleValue Value to set for property 'cycleValue'.
     */
    public void setCycleValue(String cycleValue) {
        this.cycleValue = cycleValue;
    }

    /**
     * Getter for property 'status'.
     *
     * @return Value for property 'status'.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter for property 'status'.
     *
     * @param status Value to set for property 'status'.
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * Getter for property 'startupStatus'.
     *
     * @return Value for property 'startupStatus'.
     */
    public Integer getStartupStatus() {
        return startupStatus;
    }

    /**
     * Setter for property 'startupStatus'.
     *
     * @param startupStatus Value to set for property 'startupStatus'.
     */
    public void setStartupStatus(Integer startupStatus) {
        this.startupStatus = startupStatus;
    }
}
