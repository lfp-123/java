package com.newland.boss.cib.crmp.domain;

import java.util.Date;

/**
 * Copyright (c) 2018,　新大陆软件工程公司 All rights reserved。
 *
 * @author cby
 * @version V1.0
 * @Title: ServerCfg
 * @Package com.newland.boss.cib.crmp.domain
 * @Description: 服务器配置表
 * @date 2018/8/17
 */
public class ServerCfg {
    private Integer serverId;
    private Integer serverType;
    private String hostName;
    private String userName;
    private String password;
    private Integer maxTimeout;
    private Integer retryInterval;
    private Integer status;
    private String srcPath;
    private String destPath;
    private String backupPath;
    private Integer transferMode;
    private Integer transProtocol;
    private Integer fileType;
    private Integer operatorId;
    private Date createTime;

    /**
     * Getter for property 'serverId'.
     *
     * @return Value for property 'serverId'.
     */
    public Integer getServerId() {
        return serverId;
    }

    /**
     * Setter for property 'serverId'.
     *
     * @param serverId Value to set for property 'serverId'.
     */
    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    /**
     * Getter for property 'serverType'.
     *
     * @return Value for property 'serverType'.
     */
    public Integer getServerType() {
        return serverType;
    }

    /**
     * Setter for property 'serverType'.
     *
     * @param serverType Value to set for property 'serverType'.
     */
    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    /**
     * Getter for property 'hostName'.
     *
     * @return Value for property 'hostName'.
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Setter for property 'hostName'.
     *
     * @param hostName Value to set for property 'hostName'.
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Getter for property 'userName'.
     *
     * @return Value for property 'userName'.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for property 'userName'.
     *
     * @param userName Value to set for property 'userName'.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for property 'maxTimeout'.
     *
     * @return Value for property 'maxTimeout'.
     */
    public Integer getMaxTimeout() {
        return maxTimeout;
    }

    /**
     * Setter for property 'maxTimeout'.
     *
     * @param maxTimeout Value to set for property 'maxTimeout'.
     */
    public void setMaxTimeout(Integer maxTimeout) {
        this.maxTimeout = maxTimeout;
    }

    /**
     * Getter for property 'retryInterval'.
     *
     * @return Value for property 'retryInterval'.
     */
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Setter for property 'retryInterval'.
     *
     * @param retryInterval Value to set for property 'retryInterval'.
     */
    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
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
     * Getter for property 'srcPath'.
     *
     * @return Value for property 'srcPath'.
     */
    public String getSrcPath() {
        return srcPath;
    }

    /**
     * Setter for property 'srcPath'.
     *
     * @param srcPath Value to set for property 'srcPath'.
     */
    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    /**
     * Getter for property 'destPath'.
     *
     * @return Value for property 'destPath'.
     */
    public String getDestPath() {
        return destPath;
    }

    /**
     * Setter for property 'destPath'.
     *
     * @param destPath Value to set for property 'destPath'.
     */
    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    /**
     * Getter for property 'backupPath'.
     *
     * @return Value for property 'backupPath'.
     */
    public String getBackupPath() {
        return backupPath;
    }

    /**
     * Setter for property 'backupPath'.
     *
     * @param backupPath Value to set for property 'backupPath'.
     */
    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    /**
     * Getter for property 'transferMode'.
     *
     * @return Value for property 'transferMode'.
     */
    public Integer getTransferMode() {
        return transferMode;
    }

    /**
     * Setter for property 'transferMode'.
     *
     * @param transferMode Value to set for property 'transferMode'.
     */
    public void setTransferMode(Integer transferMode) {
        this.transferMode = transferMode;
    }

    /**
     * Getter for property 'transProtocol'.
     *
     * @return Value for property 'transProtocol'.
     */
    public Integer getTransProtocol() {
        return transProtocol;
    }

    /**
     * Setter for property 'transProtocol'.
     *
     * @param transProtocol Value to set for property 'transProtocol'.
     */
    public void setTransProtocol(Integer transProtocol) {
        this.transProtocol = transProtocol;
    }

    /**
     * Getter for property 'fileType'.
     *
     * @return Value for property 'fileType'.
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * Setter for property 'fileType'.
     *
     * @param fileType Value to set for property 'fileType'.
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
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
}
