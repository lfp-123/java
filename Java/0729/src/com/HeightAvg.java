package com;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class HeightAvg {
    public double calAvgHeight(Students[] students){
        double sum=0;
        for (int i = 0; i <students.length ; i++) {
            Students s = students[i];
            sum +=s.height;
        }
        return sum/3;
    }
}
