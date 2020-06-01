package com.homework.HomeWork3;

/**
 * @author asus
 * @date 2019/7/26 19:16
 */
public class Calculator {
    double digtal1;
    double digtal2;
    String symbols;
    double result;
    String a="+";
    String b="-";
    String c="*";
    String d="/";


    public double Calculator(){
        if(symbols.equals(a)){result= digtal1+digtal2;}
        if(symbols.equals(b)){ result=  digtal1-digtal2;}
        if(symbols.equals(c)){ result = digtal1*digtal2;}
        if(symbols.equals(d)){ result= digtal1/digtal2;}
        return  result;
    }
}
