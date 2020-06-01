package com.seecen.dd.service;

import java.text.DecimalFormat;

/**
 * @author ${林锋鹏}
 * @Title: Common
 * @ProjectName Java
 * @date 2019/8/17 9:47
 */
public class Common {
    /*
    类型格式化方法
     */public static String dataFormat(double data){
        DecimalFormat format = new DecimalFormat("#0.00");
        return  format.format(data);
    }

    public static  double sub(double sum1,double sum2){
         return  (sum1*10-sum2*10)/10;
    }
}
