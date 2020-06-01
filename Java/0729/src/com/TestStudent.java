package com;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class TestStudent {
    public static void main(String[] args) {
     Students students =   new Students();
//        students.javaScore= 80;
//        students.htmlScore= 40;
//        students.cssScore= 60;
//        StudentCalc calc = new StudentCalc();
//        double avg = calc.calc(students);
//        System.out.println("平均分:" + avg);
        Students s1 = new Students();
        Students s2 = new Students();
        Students s3 = new Students();
        s1.height =192;
        s2.height =192;
        s3.height =192;
        Students[] names = new Students[]{s1,s2,s3};
        HeightAvg heightAvg = new HeightAvg();
        double sun=  heightAvg.calAvgHeight(names);
        System.out.println(sun);

    }
}
