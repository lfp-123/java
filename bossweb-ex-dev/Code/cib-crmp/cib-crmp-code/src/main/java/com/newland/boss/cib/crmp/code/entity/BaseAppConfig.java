package com.newland.boss.cib.crmp.code.entity;

/**
 * @author ${林锋鹏}
 * @Title: BaseAppConfig
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/8 21:50
 */
public class BaseAppConfig {
    private Integer systemId;
    private String systemName;
    private String appName;
    private String appInstanceName;
    private String configKey;
    private String configValue;
    private String configType;
    private String configPath;
    private Integer configSeq;

    @Override
    public String toString() {
        return "BaseAppConfig{" +
                "systemId=" + systemId +
                ", systemName='" + systemName + '\'' +
                ", appName='" + appName + '\'' +
                ", appInstanceName='" + appInstanceName + '\'' +
                ", configKey='" + configKey + '\'' +
                ", configValue='" + configValue + '\'' +
                ", configType='" + configType + '\'' +
                ", configPath='" + configPath + '\'' +
                ", configSeq=" + configSeq +
                '}';
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
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

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public Integer getConfigSeq() {
        return configSeq;
    }

    public void setConfigSeq(Integer configSeq) {
        this.configSeq = configSeq;
    }

    public BaseAppConfig(Integer systemId, String systemName, String appName, String appInstanceName, String configKey, String configValue, String configType, String configPath, Integer configSeq) {

        this.systemId = systemId;
        this.systemName = systemName;
        this.appName = appName;
        this.appInstanceName = appInstanceName;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configType = configType;
        this.configPath = configPath;
        this.configSeq = configSeq;
    }

    public BaseAppConfig() {

    }
}
