package com.homework;

/**
 * @author ${林锋鹏}
 * @Title: FactoryPatterDemo
 * @ProjectName 简单工厂模式 和 抽象工厂模式
 * @date 2019/8/13 19:22
 */
public class Test {
    public static void main(String[] args) {
          //获取形状工厂
        AbstractFactory shape =  FactoryProducer.getFactory("Shape");
        //获取一个Square的对象
        Shape square = shape.getShape("Square");
        //使用Square的对象调用显示
        square.draw();
        //获取一个三角形的对象
        Shape rectangle = shape.getShape("Rectangle");
        //调用draw方法
        rectangle.draw();
        // 获取颜色工厂
        AbstractFactory color = FactoryProducer.getFactory("Color");
        //获取颜色对象
        Color red = color.getColor("Red");
        //调用fill方法r
        red.fill();



    }
}
