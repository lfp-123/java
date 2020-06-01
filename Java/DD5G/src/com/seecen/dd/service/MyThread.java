package com.seecen.dd.service;

import com.seecen.dd.cons.getData;

import java.util.TimerTask;

/**
 * @author ${林锋鹏}
 * @Title: MyThread
 * @ProjectName Java
 * @date 2019/8/18 17:04
 */
public class MyThread extends TimerTask {

    @Override
    public void run() {
        getData data = new getData();
        System.out.println();
        System.out.println("系统时间群发提示："+data.getDatas());
    }
}
