package com.seecen.dd.cons;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

/**
 * @author ${林锋鹏}
 * @Title: getData
 * @ProjectName 获取系统时间
 * @date 2019/8/18 16:23
 */
public class getData {

    public String getDatas(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return     df.format(System.currentTimeMillis());

    }
}
