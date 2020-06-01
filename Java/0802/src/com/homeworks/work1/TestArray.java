package com.homeworks.work1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/4 15:07
 */
public class TestArray {
    public static void main(String[] args) {
       List<String> list =new ArrayList<String>();
        list.add("迪丽热巴");
        list.add("古力娜扎");
        list.add("那五克热");


        for (String a:
          list   ) {
            System.out.print(a+" ");
        }
      /*第二种的遍历方法
       */

        String[] strings = new String[list.size()];
        list.toArray(strings);
        for (int i = 0; i <strings.length ; i++) {
            System.out.print(strings[i]+" ");
        }
            /*迭代器

             */
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()){
            System.out.print(ite.next()+" ");
        }
    }

}
