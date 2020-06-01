package cn.sc.afternoon;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: Time
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 14:36
 */
public class Time {
    public static void main(String[] args) {
        Date date = new Date();
        int year = date.getYear();
        int month = date.getMonth();
        int date1 = date.getDate();
        System.out.println(year+ " "+month +" "+ date1);
        //格式化日期 SimpleDateFormat String <==> date
        //格式化参数有哪些 y年 M月 d日 hh12进制时，HH24小时 mm分 S毫秒 a上午/下午
        String time = "yyyy-MM-dd a HH：mm:s";
        SimpleDateFormat sim = new SimpleDateFormat(time);
        String format = sim.format(date);
        System.out.println(format);

    }
}
