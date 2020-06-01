package com.sc.spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ${林锋鹏}
 * @Title: BeanFactory
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/12 14:39
 */
public class BeanFactory {

    public  static  Object getBean(String id){
        Properties p = new Properties();
        Object us=null;
        InputStream rs = BeanFactory.class.getResourceAsStream("object.properties");
        try {
            p.load(rs);
            String className = p.getProperty(id);
            us =Class.forName(className).newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  us;
    }
}
