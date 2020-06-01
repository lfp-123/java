package com.sc.mor;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @author ${林锋鹏}
 * @Title: Reflect
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/20 11:04
 */
public class Reflect {
    public static void main(String[] args) throws ClassNotFoundException ,NoSuchMethodException{
        //类的描述
        Class<?> userClass = Class.forName("com.sc.mor.User");
        String simpleName = userClass.getSimpleName(); //类名
        String name = userClass.getName(); //获取全类名
        System.out.println("全类名："+name+" 类名："+simpleName);
        System.out.println("---------------------------------");
        Method[] declaredMethods = userClass.getDeclaredMethods();//获取该类中的所有方法，不包括继承的方法
        Method[] methods = userClass.getMethods();// 获取所有方法
        for (Method method : declaredMethods) {
            System.out.println("方法名：" +
                    method.getName()+"，返回值类型：" +
                    method.getReturnType()+"，参数列表：" +
                    Arrays.toString(method.getParameterTypes())+"，");
        }
        System.out.println("---------------------------------");
        Method m1 = userClass.getDeclaredMethod("setRole", String.class); //方法名 ，参数类型
        Class<?> superclass = userClass.getSuperclass(); //获取父类
        System.out.println("父类："+superclass);
        Constructor<?>[] constructors = userClass.getConstructors(); //返回构造方法
        for (Constructor<?> c : constructors) {
            System.out.println("方法名：" +
                    c.getName()+"，参数列表：" +
                    Arrays.toString(c.getTypeParameters())+"，");
        }
        Field[] declaredFields = userClass.getDeclaredFields(); //获取属性
        for (Field declaredField : declaredFields) {
            System.out.println("属性名：" +
                    declaredField.getName()+"，类型：" +
                    declaredField.getType()+"，");
        }

    }
    @Test
    public void testReflect() {
        //1.通过反射创建对象  获取class对象
        Class<User> userClass = User.class;
        //2.通过class对象 newInstance() 运行时底层还是会去调用无参构造，构造如果私有会报错
        try {
            User u = userClass.newInstance();
            //通过反射对象的class对象获取其中一个属性  参数为属性名
            Field un =userClass.getDeclaredField("name");
            //通过反射赋值属性， 如果属性是私有的   需要特殊处理
            un.setAccessible(true);  //代表允许设置私有属性
            //un.set(目标对象，赋值的实际内容)
            un.set(un,"张三123");

            System.out.println(u.getName());
            //调用方法
            Method m = userClass.getDeclaredMethod("setName", String.class);
            m.invoke(u,"123456");
            System.out.println(u.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
