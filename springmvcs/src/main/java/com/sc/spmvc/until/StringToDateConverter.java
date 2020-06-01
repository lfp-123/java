package com.sc.spmvc.until;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ${林锋鹏}
 * @Title: StringToDateConverter
 * @ProjectName Java
 * @Description: 自定类型 转换器
 * @date 2020/2/7 15:54
 */
public class StringToDateConverter implements Converter<String,Date> {
    @Nullable
    @Override
    public Date convert(String o) {
        if(o ==null){
            throw new RuntimeException("请传入数据");
        }
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
           return df.parse(o);
        } catch (ParseException e) {
           throw new RuntimeException("数据类型转换出现错误");
        }

    }
}
