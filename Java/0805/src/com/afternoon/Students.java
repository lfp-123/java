package com.afternoon;

/**
 * @author ${林锋鹏}
 * @Title: Students
 * @ProjectName Java
 * @date 2019/8/5 15:56
 */
public class Students  implements Comparable{

    int stuNo;
    String name;
    public Students(int stuNo,String name){
        this.name=name;
        this.stuNo =stuNo;
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof Students){
            return this.stuNo -((Students) o).stuNo ;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Students{" +
                "stuNo=" + stuNo +
                ", name='" + name + '\'' +
                '}';
    }
}
