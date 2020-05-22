package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: ExportUploadCdrTask
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 局数据任务表
 * @date 2018/6/4
 */
public class ExportUploadCdrTask {
    private Integer taskId;
    private Date createTime;
    private Date exportStartTime;
    private Date exportEndTime;
    private Date uploadStartTime;
    private Date updateEndTime;
    private Integer taskType;

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
     * Getter for property 'exportStartTime'.
     *
     * @return Value for property 'exportStartTime'.
     */
    public Date getExportStartTime() {
        return exportStartTime;
    }

    /**
     * Setter for property 'exportStartTime'.
     *
     * @param exportStartTime Value to set for property 'exportStartTime'.
     */
    public void setExportStartTime(Date exportStartTime) {
        this.exportStartTime = exportStartTime;
    }

    /**
     * Getter for property 'exportEndTime'.
     *
     * @return Value for property 'exportEndTime'.
     */
    public Date getExportEndTime() {
        return exportEndTime;
    }

    /**
     * Setter for property 'exportEndTime'.
     *
     * @param exportEndTime Value to set for property 'exportEndTime'.
     */
    public void setExportEndTime(Date exportEndTime) {
        this.exportEndTime = exportEndTime;
    }

    /**
     * Getter for property 'uploadStartTime'.
     *
     * @return Value for property 'uploadStartTime'.
     */
    public Date getUploadStartTime() {
        return uploadStartTime;
    }

    /**
     * Setter for property 'uploadStartTime'.
     *
     * @param uploadStartTime Value to set for property 'uploadStartTime'.
     */
    public void setUploadStartTime(Date uploadStartTime) {
        this.uploadStartTime = uploadStartTime;
    }

    /**
     * Getter for property 'updateEndTime'.
     *
     * @return Value for property 'updateEndTime'.
     */
    public Date getUpdateEndTime() {
        return updateEndTime;
    }

    /**
     * Setter for property 'updateEndTime'.
     *
     * @param updateEndTime Value to set for property 'updateEndTime'.
     */
    public void setUpdateEndTime(Date updateEndTime) {
        this.updateEndTime = updateEndTime;
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
}
