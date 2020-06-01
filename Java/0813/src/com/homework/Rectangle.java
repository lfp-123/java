package com.homework;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @author ${林锋鹏}
 * @Title: Rectangle
 * @ProjectName Java
 * @date 2019/8/13 19:15
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("This is Rectangle::draw()method.    ");
    }
}
