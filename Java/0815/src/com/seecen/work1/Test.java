package com.seecen.work1;



import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @Author 林峰鹏
 * @Date 2019/8/15 9:50
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student("bigpig", 18);

        String classPath = "com.seecen.work1.Student";
        printProperty(classPath);
    }
    public static Object createInstance(String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);
            // 创建它的对象
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  static void printProperty(String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);

            // 获取这个字节码所有的public字段(含父类)
        //    Field[] fields = clazz.getFields();
            Field[] fields = clazz.getFields();
            // 获取当前clazz的所有字段
            Field[] myfields = clazz.getDeclaredFields();
            for (Field field : myfields) {
                System.out.println(field.getName());
            }
            // 获取所有public的方法(含父类)
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }
            // 动态获取当前类的类名
            System.out.println(clazz.getName());

            Constructor<?>[] constructors = clazz.getConstructors();
            System.out.println(constructors.length);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public static String convertJson(String classPath) {
        String json = null;
        return json;
    }*/
}
