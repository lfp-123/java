package com.newland.boss.cib.crmp.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Organization {

    private Integer orgId;
    private Integer superOrgId;
    private String orgName;
    private Integer orgStatus;
    private String orgDesc;
    private Date createTime;
    private Date modifyTime;
    private Integer modifyOperatorId;
    private String orgNameFull;
    private Integer isSync;
    private Integer orgOrder;
    private String currentOrgNameFull;

    private Map<String, Organization> childOrgList;

    public Map<String, Organization> getChildOrgList() {
        return childOrgList;
    }

    public void setChildOrgList(Map<String, Organization> childOrgList) {
        this.childOrgList = childOrgList;
    }

    public Organization() {
        super();
        childOrgList = new HashMap<String, Organization>();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getSuperOrgId() {
        return superOrgId;
    }

    public void setSuperOrgId(Integer superOrgId) {
        this.superOrgId = superOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    /**
     * Getter for property 'orgNameFull'.
     *
     * @return Value for property 'orgNameFull'.
     */
    public String getOrgNameFull() {
        return orgNameFull;
    }

    /**
     * Setter for property 'orgNameFull'.
     *
     * @param orgNameFull Value to set for property 'orgNameFull'.
     */
    public void setOrgNameFull(String orgNameFull) {
        this.orgNameFull = orgNameFull;
    }


    /**
     * Getter for property 'orgOrder'.
     *
     * @return Value for property 'orgOrder'.
     */
    public Integer getOrgOrder() {
        return orgOrder;
    }

    /**
     * Setter for property 'orgOrder'.
     *
     * @param orgOrder Value to set for property 'orgOrder'.
     */
    public void setOrgOrder(Integer orgOrder) {
        this.orgOrder = orgOrder;
    }

    /**
     * Getter for property 'isSync'.
     *
     * @return Value for property 'isSync'.
     */
    public Integer getIsSync() {
        return isSync;
    }

    /**
     * Setter for property 'isSync'.
     *
     * @param isSync Value to set for property 'isSync'.
     */
    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    /**
     * Getter for property 'currentOrgNameFull'.
     *
     * @return Value for property 'currentOrgNameFull'.
     */
    public String getCurrentOrgNameFull() {
        return currentOrgNameFull;
    }

    /**
     * Setter for property 'currentOrgNameFull'.
     *
     * @param currentOrgNameFull Value to set for property 'currentOrgNameFull'.
     */
    public void setCurrentOrgNameFull(String currentOrgNameFull) {
        this.currentOrgNameFull = currentOrgNameFull;
    }

    @Override
    public String toString() {
        return "Organization [orgId=" + orgId + ", superOrgId=" + superOrgId + ", orgName=" + orgName + ", orgStatus="
                + orgStatus + ", orgDesc=" + orgDesc + ", createTime=" + createTime + ", modifyTime=" + modifyTime
                + ", modifyOperatorId=" + modifyOperatorId + "]";
    }

}
