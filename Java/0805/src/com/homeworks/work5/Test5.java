package com.homeworks.work5;

import java.util.HashMap;

/**
 * @author ${林锋鹏}
 * @Title: Test5
 * @ProjectName Java
 * @date 2019/8/5 19:38
 */
public class Test5 {
    public static void main(String[] args) {

        HashMap hashMap = new HashMap();
        hashMap.put("US","美国");
        hashMap.put("CN","中国");
        hashMap.put("FR","法国");
        hashMap.put("RU","俄罗斯");

        System.out.println("US代表的国家是：");
        System.out.println(hashMap.get("US"));


    }
}
