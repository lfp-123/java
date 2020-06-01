package com.homework;

/**
 * @author ${林锋鹏}
 * @Title: Factory
 * @ProjectName Java
 * @date 2019/8/13 19:18
 */
public class shapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }
    @Override
        public Shape getShape(String shape){
            if(shape==null){
                return null;
            }else  if(shape.equalsIgnoreCase("Circle")){
                return  new Circle();
            }else  if(shape.equalsIgnoreCase("Rectangle")){
                return  new Rectangle();
            }else if(shape.equalsIgnoreCase("Square")){
                return  new Square();
            }
            return  null;
    }
}
