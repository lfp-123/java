package com.homeworks.work6;

import java.util.HashMap;

/**
 * @author ${林锋鹏}
 * @Title: Test6
 * @ProjectName Java
 * @date 2019/8/5 19:43
 */
public class Test6 {
    public static void main(String[] args) {

        HashMap hashMap = new HashMap();
        hashMap.put("1","鼠");
        hashMap.put("2","牛");
        hashMap.put("3","虎");
        hashMap.put("4","兔");
        hashMap.put("5","龙");
        hashMap.put("6","蛇");
        hashMap.put("7","马");

        System.out.println("一共有"+hashMap.size()+"种");

        for (Object a: hashMap.keySet()) {
            System.out.println("序号："+a+"动物："+hashMap.get(a));
        }
        System.out.println("查找龙");
        if( hashMap.containsKey("5")){
            System.out.println("找到了");
        }else {
            System.out.println("没有");
        }




       
    }
}
