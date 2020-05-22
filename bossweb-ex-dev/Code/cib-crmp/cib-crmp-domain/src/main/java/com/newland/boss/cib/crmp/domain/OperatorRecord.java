package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: OperatorRecord
 * @Package com.newland.boss.cib.crmp.domain
 * @Description: 用户操作历史表
 * @date 2018/10/17
 */
public class OperatorRecord {
    private Integer operatorId;
    private Date createTime;
    private Date modifyTime;
    private Integer syncStatus;
    private Integer status;
    private String directNumber;
    private String directNumberModify;
    private Integer orgId;
    private Integer month;
    private Integer count;
    // 用户名
    private String notesId;
    // 部门id
    private String orgName;

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
     * Getter for property 'modifyTime'.
     *
     * @return Value for property 'modifyTime'.
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * Setter for property 'modifyTime'.
     *
     * @param modifyTime Value to set for property 'modifyTime'.
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * Getter for property 'syncStatus'.
     *
     * @return Value for property 'syncStatus'.
     */
    public Integer getSyncStatus() {
        return syncStatus;
    }

    /**
     * Setter for property 'syncStatus'.
     *
     * @param syncStatus Value to set for property 'syncStatus'.
     */
    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
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
     * Getter for property 'directNumberModify'.
     *
     * @return Value for property 'directNumberModify'.
     */
    public String getDirectNumberModify() {
        return directNumberModify;
    }

    /**
     * Setter for property 'directNumberModify'.
     *
     * @param directNumberModify Value to set for property 'directNumberModify'.
     */
    public void setDirectNumberModify(String directNumberModify) {
        this.directNumberModify = directNumberModify;
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
     * Getter for property 'month'.
     *
     * @return Value for property 'month'.
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Setter for property 'month'.
     *
     * @param month Value to set for property 'month'.
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * Getter for property 'count'.
     *
     * @return Value for property 'count'.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Setter for property 'count'.
     *
     * @param count Value to set for property 'count'.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Getter for property 'notesId'.
     *
     * @return Value for property 'notesId'.
     */
    public String getNotesId() {
        return notesId;
    }

    /**
     * Setter for property 'notesId'.
     *
     * @param notesId Value to set for property 'notesId'.
     */
    public void setNotesId(String notesId) {
        this.notesId = notesId;
    }

    /**
     * Getter for property 'orgName'.
     *
     * @return Value for property 'orgName'.
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * Setter for property 'orgName'.
     *
     * @param orgName Value to set for property 'orgName'.
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
