package com.homework;

/**
 * @author ${林锋鹏}
 * @Title: AbstractFactory
 * @ProjectName Java
 * @date 2019/8/13 19:36
 */
public  abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
