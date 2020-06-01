package com.homeworks.work9;

import java.util.HashMap;

/**
 * @author ${林锋鹏}
 * @Title: Test9
 * @ProjectName Java
 * @date 2019/8/5 20:06
 */
public class Test9 {
    public static void main(String[] args) {

        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("US","美国");
        hashMap.put("CN","中国");
        hashMap.put("FR","法国");
        hashMap.put("RU","俄罗斯");
        System.out.println(hashMap.keySet());


        System.out.println("US代表的国家是：");
        System.out.println(hashMap.get("US"));
    }
}
