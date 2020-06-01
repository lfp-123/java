package com.homeworks.work13;

import java.util.*;

/**
 * @author ${林锋鹏}
 * @Title: Test10
 * @ProjectName Java
 * @date 2019/8/5 20:12
 */
public class Test13 {
    public static void main(String[] args) {
        Students student1 = new Students("迪丽热巴", 29,"女");
        Students student2= new Students("古力娜扎", 26,"女");
        Students student3= new Students("欧阳锋", 40,"男");
        Students student4 = new Students("张三风", 70,"男");
        Students student5 = new Students("胡歌", 38,"男");
        HashMap<String,Students> students = new HashMap<>();
        students.put("迪丽热巴",student1);
        students.put("古力娜扎",student2);
        students.put("欧阳锋",student3);
        students.put("张三风",student4);
        students.put("胡歌",student5);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要查询的学生：");
        String name = scanner.next();
        if(students.containsKey(name)){

            System.out.println(students.get(name).toString());
        }else {
                System.out.println("查无此人");

        }
        for (Map.Entry<String,Students> entry:
            students.entrySet()  ) {
            System.out.println(entry.getKey()+" ");
            System.out.println(students.get(name).toString());
        }

    }
}
