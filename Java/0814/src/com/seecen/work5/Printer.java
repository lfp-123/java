package com.seecen.work5;

/**
 * @author ${林锋鹏}
 * @Title: Printer
 * @ProjectName Java
 * @date 2019/8/14 15:58
 */
public class Printer {
    private boolean flag = true; // true为关羽
    private int i = 0; // 计数器
    public synchronized void printer(String name) throws InterruptedException {
        if(name.equals("赵云") && flag) {
            this.wait();
        }
        System.out.println(name );
        i++;
        if(i == 3) {
            i = 0;
            flag = false; // 让赵云执行
            this.notify();
            this.wait();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



        }

