package com.newland.boss.cib.crmp.code.entity;

import javax.persistence.Entity;

/**
 * @author ${林锋鹏}
 * @Title: APP
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/7 9:46
 */
@Entity
public class AppConfig {

    private Integer configType;
    private String  configKey;
    private String configValue;
    private String configPath;
    private String appName;
    private String appInstanceName;
    private String configDesc;
    private Integer systemId;
    private Integer groupId;
    private Integer configSeq;
    private Dict dict;

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }

    public AppConfig() {
        super();

    }

    public AppConfig(Integer configSeq, Integer configType, String configKey, String configValue, String configPath, String appName, String appInstanceName, String configDesc, Integer systemId, Integer groupId, Dict dict) {
        this.configSeq = configSeq;
        this.configType = configType;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configPath = configPath;
        this.appName = appName;
        this.appInstanceName = appInstanceName;
        this.configDesc = configDesc;
        this.systemId = systemId;
        this.groupId = groupId;
        this.dict = dict;
    }


    public Integer getConfigSeq() {
        return configSeq;
    }

    public void setConfigSeq(Integer configSeq) {
        this.configSeq = configSeq;
    }
    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppInstanceName() {
        return appInstanceName;
    }

    public void setAppInstanceName(String appInstanceName) {
        this.appInstanceName = appInstanceName;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }


    @Override
    public String toString() {
        return "AppConfig{" +
                "configType=" + configType +
                ", configKey='" + configKey + '\'' +
                ", configValue='" + configValue + '\'' +
                ", configPath='" + configPath + '\'' +
                ", appName='" + appName + '\'' +
                ", appInstanceName='" + appInstanceName + '\'' +
                ", configDesc='" + configDesc + '\'' +
                ", systemId=" + systemId +
                ", groupId=" + groupId +
                ", configSeq=" + configSeq +
                ", dict=" + dict +
                '}';
    }
}
