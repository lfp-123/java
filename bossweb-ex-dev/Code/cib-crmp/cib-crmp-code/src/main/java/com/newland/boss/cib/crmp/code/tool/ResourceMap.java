package com.newland.boss.cib.crmp.code.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ${林锋鹏}
 * @Title: ResourceMap
 * @ProjectName Code
 * @Description: TODO
 * @date 2020/5/6 13:48
 */
public  class ResourceMap {
    private static Map<String,String> jdbcMap;

    public static Map<String, String> getJdbcMap() {
        return jdbcMap;
    }

    public static void setJdbcMap(Map<String, String> jdbcMap) {
        ResourceMap.jdbcMap = jdbcMap;
    }

    static {
            jdbcMap =  new HashMap<String,String>();
            jdbcMap.put("name","");
            jdbcMap.put("username","");
            jdbcMap.put("port","");
            jdbcMap.put("driver","null");
            jdbcMap.put("type","null");
            jdbcMap.put("url","null");
            jdbcMap.put("password","null");
    }




}
