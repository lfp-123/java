package com.seencen.morning.work2;

/**
 * @author asus
 * @Title: Pet
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 14:08
 */
public interface   Pet  {

    default void hello(){
        System.out.println("hello");
    }
    abstract  void introduce();

}
