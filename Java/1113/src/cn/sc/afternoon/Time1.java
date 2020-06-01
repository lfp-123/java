package cn.sc.afternoon;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: Time1
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 14:45
 */
public class Time1 {
    public static void main(String[] args) {
        //实现日历对象实现日期
        Calendar is = Calendar.getInstance();
        //获取日历对象
        //将日历对象存入日期

        is.setTime(new Date());
        int i = is.get(Calendar.DAY_OF_YEAR);
        int month = is.get(Calendar.MONTH);
        int i1 = is.get(Calendar.DATE);

        System.out.println(i+" "+month+" "+i1);
        //jdk 1.8
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
        LocalDate parse = LocalDate.parse("2019-08-15T20:15:30", DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(parse);



    }
}
