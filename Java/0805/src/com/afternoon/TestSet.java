package com.afternoon;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ${林锋鹏}
 * @Title: Set
 * @ProjectName Java
 * @date 2019/8/5 15:55
 */
    public class TestSet {
    public static void main(String[] args) {
       Set hashSet = new TreeSet();
        hashSet.add(new Students(1,"李白"));
        hashSet.add(new Students(2,"李黑"));
        hashSet.add(new Students(4,"李明"));
        hashSet.add(new Students(5,"李天"));
        hashSet.add(new Students(3,"李书"));
        hashSet.add(new Students(6,"李好"));
        hashSet.add(new Students(8,"李说"));

        hashSet.forEach(aa-> System.out.println(aa));


    }
}
