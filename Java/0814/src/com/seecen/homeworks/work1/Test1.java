package com.seecen.homeworks.work1;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/14 17:36
 */
public class Test1 {


        public static void main(String[] args) throws InterruptedException {

            Object a=new Object();
            Object b=new Object();
            Object c=new Object();
            PrintSelf pb=new PrintSelf("B",b,c);
            PrintSelf pa=new PrintSelf("A",c,a);
            PrintSelf pc=new PrintSelf("C",a,b);

            new Thread(pa).start();
            Thread.sleep(1000);
            new Thread(pb).start();
            Thread.sleep(1000);
            new Thread(pc).start();
        }
    }

