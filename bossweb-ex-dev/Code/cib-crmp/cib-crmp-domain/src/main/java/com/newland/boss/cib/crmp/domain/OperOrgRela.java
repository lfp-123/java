package com.newland.boss.cib.crmp.domain;

import java.util.Date;

//操作员机构
public class OperOrgRela {

    private Integer operatorId;
    private Integer orgId;
    private Integer relaStatus;
    private Date createTime;
    private Date modifyTime;
    private Integer modifyOperatorId;
    private Date inureDate;
    private Date expireDate;

    public OperOrgRela() {
        super();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getRelaStatus() {
        return relaStatus;
    }

    public void setRelaStatus(Integer relaStatus) {
        this.relaStatus = relaStatus;
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
     * Getter for property 'inureDate'.
     *
     * @return Value for property 'inureDate'.
     */
    public Date getInureDate() {
        return inureDate;
    }

    /**
     * Setter for property 'inureDate'.
     *
     * @param inureDate Value to set for property 'inureDate'.
     */
    public void setInureDate(Date inureDate) {
        this.inureDate = inureDate;
    }

    /**
     * Getter for property 'expireDate'.
     *
     * @return Value for property 'expireDate'.
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Setter for property 'expireDate'.
     *
     * @param expireDate Value to set for property 'expireDate'.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "OperOrgRela [operatorId=" + operatorId + ", orgId=" + orgId + ", relaStatus=" + relaStatus
                + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", modifyOperatorId=" + modifyOperatorId
                + "]";
    }


}
