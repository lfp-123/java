package com.newland.boss.cib.crmp.domain;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: OperOrgRelView
 * @Package com.newland.boss.cib.crmp.model
 * @Description: 用户机构关系表
 * @date 2018/7/18
 */
public class OperOrgRelView {
    private Integer operatorId;
    private Integer orgId;
    private String userId;
    private String directNumber;
    private Integer count;
    /**
     * 重复标识判断
     */
    private boolean flag;
    /**
     * 当前合账任务时候用户号码
     */
    private Integer operDirectNumber;

    public OperOrgRelView() {
        this.flag = false;
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
     * Getter for property 'flag'.
     *
     * @return Value for property 'flag'.
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * Setter for property 'flag'.
     *
     * @param flag Value to set for property 'flag'.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Getter for property 'operDirectNumber'.
     *
     * @return Value for property 'operDirectNumber'.
     */
    public Integer getOperDirectNumber() {
        return operDirectNumber;
    }

    /**
     * Setter for property 'operDirectNumber'.
     *
     * @param operDirectNumber Value to set for property 'operDirectNumber'.
     */
    public void setOperDirectNumber(Integer operDirectNumber) {
        this.operDirectNumber = operDirectNumber;
    }
}
