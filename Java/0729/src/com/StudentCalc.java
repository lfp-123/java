package com;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class StudentCalc {
        public double calc(Students students){
            return (students.cssScore+students.htmlScore
                    +students.javaScore)/3;
        }
}
