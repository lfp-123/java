package com.homeworks.work2;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Java
 * @date 2019/8/4 17:56
 */
public class Test3<T> {
    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }


        public static void main(String[] args) {
            Test3<Integer> integerBox = new Test3<Integer>();
            Test3<String> stringBox = new Test3<String>();

            integerBox.add(new Integer(10));
            stringBox.add(new String("菜鸟教程"));

            System.out.printf("整型值为 :%d\n\n", integerBox.get());
            System.out.printf("字符串为 :%s\n", stringBox.get());
        }
    }

