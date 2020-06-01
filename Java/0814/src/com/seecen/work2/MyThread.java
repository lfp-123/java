package com.seecen.work2;

/**
 * @author ${林锋鹏}
 * @Title: Mythread
 * @ProjectName Java
 * @date 2019/8/14 10:35
 */
public class MyThread extends Thread{
    public volatile   boolean flag=false;
    public int num=0;
    @Override
    public void run() {
        while (!flag){
            num +=1;

        }
        System.out.println(num);
    }


}
