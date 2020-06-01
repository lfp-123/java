package com.afternoon;

import java.util.*;

/**
 * @author ${林锋鹏}
 * @Title: TestMap
 * @ProjectName Java
 * @date 2019/8/5 16:38
 */
public class TestMap {
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("1","林一");
        hashMap.put("2","林二");
        hashMap.put("3","林三");
        hashMap.put("4","林四");
        hashMap.put("5","林五");
        hashMap.put("6","林六");
        hashMap.put("7","林七");
        hashMap.put("8","林八");

        Set set =hashMap.keySet();
        set.forEach(aa-> System.out.print(aa+" "));
        System.out.println();
        Collection map= hashMap.values();
        System.out.println(map);

        map.forEach(System.out::print);
        System.out.println();
        Iterator iterator = map.iterator();
       while (iterator.hasNext()){
           System.out.print(iterator.next()+" ");
        }

        for (String keys:hashMap.keySet()) {
            System.out.println(keys +" "+hashMap.get(keys));
        }
    }
}
