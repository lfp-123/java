package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: SummingSettleTask
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 详单合账与结算摊分任务表 TableName-summing_settle_task
 * @date 2018/5/15
 */
public class SummingSettleTask {
    private Integer taskId;
    private Integer billMonth;
    private Integer billDay;
    private Integer cdrType;
    private Integer taskType;
    private Integer taskSrc;
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
     * Getter for property 'taskType'.
     *
     * @return Value for property 'taskType'.
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * Setter for property 'taskType'.
     *
     * @param taskType Value to set for property 'taskType'.
     */
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * Getter for property 'taskSrc'.
     *
     * @return Value for property 'taskSrc'.
     */
    public Integer getTaskSrc() {
        return taskSrc;
    }

    /**
     * Setter for property 'taskSrc'.
     *
     * @param taskSrc Value to set for property 'taskSrc'.
     */
    public void setTaskSrc(Integer taskSrc) {
        this.taskSrc = taskSrc;
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

	@Override
	public String toString() {
		return "SummingSettleTask [taskId=" + taskId + ", billMonth=" + billMonth + ", billDay=" + billDay
				+ ", cdrType=" + cdrType + ", taskType=" + taskType + ", taskSrc=" + taskSrc + ", createTime="
				+ createTime + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
    
}
