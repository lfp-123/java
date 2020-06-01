package com.homeworks.work1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ${林锋鹏}
 * @Title: TestMap
 * @ProjectName Java
 * @date 2019/8/4 16:48
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("1","中国");
        map.put("2","美国");
        map.put("3","日本");


        System.out.println("第一种遍历方法：");
        for (String  key:
             map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
}


