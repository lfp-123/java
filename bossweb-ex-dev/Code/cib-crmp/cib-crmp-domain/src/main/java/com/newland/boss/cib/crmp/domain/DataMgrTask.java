package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: DataMgrTask
 * @Package com.newland.boss.cib.crmp.domain
 * @Description: 局数据采集定时任务表
 * @date 2018/9/28
 */
public class DataMgrTask {
    private Integer taskId;
    private Integer operNumber;
    private Integer orgNumber;
    private Date createTime;
    private Date startTime;
    private Date endTime;

    /**
     * Getter for property 'taskId'.
     *
     * @return Value for property 'taskId'.
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * Setter for property 'taskId'.
     *
     * @param taskId Value to set for property 'taskId'.
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for property 'operNumber'.
     *
     * @return Value for property 'operNumber'.
     */
    public Integer getOperNumber() {
        return operNumber;
    }

    /**
     * Setter for property 'operNumber'.
     *
     * @param operNumber Value to set for property 'operNumber'.
     */
    public void setOperNumber(Integer operNumber) {
        this.operNumber = operNumber;
    }

    /**
     * Getter for property 'orgNumber'.
     *
     * @return Value for property 'orgNumber'.
     */
    public Integer getOrgNumber() {
        return orgNumber;
    }

    /**
     * Setter for property 'orgNumber'.
     *
     * @param orgNumber Value to set for property 'orgNumber'.
     */
    public void setOrgNumber(Integer orgNumber) {
        this.orgNumber = orgNumber;
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
     * Getter for property 'startTime'.
     *
     * @return Value for property 'startTime'.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter for property 'startTime'.
     *
     * @param startTime Value to set for property 'startTime'.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for property 'endTime'.
     *
     * @return Value for property 'endTime'.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter for property 'endTime'.
     *
     * @param endTime Value to set for property 'endTime'.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
