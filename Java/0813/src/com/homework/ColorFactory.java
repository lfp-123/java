package com.homework;

/**
 * @author ${林锋鹏}
 * @Title: ColorFactory
 * @ProjectName Java
 * @date 2019/8/13 19:40
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        if(color==null){
            return null;
        }else if(color.equalsIgnoreCase("Red")){
            return new Red();
        } else if (color.equalsIgnoreCase("Yellow")) {
            return new Yellow();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
