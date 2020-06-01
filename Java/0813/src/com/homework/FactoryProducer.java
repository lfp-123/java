package com.homework;

/**
 * @author ${林锋鹏}
 * @Title: FactoryProducer
 * @ProjectName Java
 * @date 2019/8/13 19:43
 */
public class FactoryProducer  {
    public static AbstractFactory getFactory(String choice){
        if(choice==null){
            return null;
        }else  if(choice.equalsIgnoreCase("Color")){
            return  new ColorFactory();
        }else if(choice.equalsIgnoreCase("Shape")){
            return new shapeFactory();
        }
        return null;
    }
}
