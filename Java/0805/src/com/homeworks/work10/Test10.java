package com.homeworks.work10;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ${林锋鹏}
 * @Title: Test10
 * @ProjectName Java
 * @date 2019/8/5 20:12
 */
public class Test10 {
    public static void main(String[] args) {
        Students student1 = new Students("迪丽热巴", 29,"女");
        Students student2= new Students("古力娜扎", 26,"女");
        Students student3= new Students("欧阳锋", 40,"男");
        Students student4 = new Students("张三风", 70,"男");
        Students student5 = new Students("胡歌", 38,"男");
        ArrayList<Students> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        Iterator<Students> studentsIterator = students.iterator();
        while (studentsIterator.hasNext()){
                Students stu = studentsIterator.next();
            System.out.println(stu.toString());
        }


    }
}
