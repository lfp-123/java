package com.newland.boss.cib.crmp.domain;

/**话单处理接口
 *
 */
public interface StandardCdr {

    void setCdrFlag(int cdrFlag);

    int getCdrFlag();

    void setCdrType(int cdrType);

    int getCdrType();

    void setFileName(String fileName);

    String getFileName();

}
