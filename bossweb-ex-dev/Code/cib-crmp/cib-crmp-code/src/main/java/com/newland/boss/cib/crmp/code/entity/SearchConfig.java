package com.newland.boss.cib.crmp.code.entity;

/**
 * @author ${林锋鹏}
 * @Title: SearchConfig
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/9 0:00
 */
public class SearchConfig {
    private String systemId;
    private String appName;
    private String appInstanceName;

    @Override
    public String toString() {
        return "SearchConfig{" +
                "systemId='" + systemId + '\'' +
                ", appName='" + appName + '\'' +
                ", appInstanceName='" + appInstanceName + '\'' +
                '}';
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
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

    public SearchConfig(String systemId, String appName, String appInstanceName) {

        this.systemId = systemId;
        this.appName = appName;
        this.appInstanceName = appInstanceName;
    }

    public SearchConfig() {

    }
}
