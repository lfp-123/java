package com.homework;

/**
 * @author asus
 * @date 2019/7/28 11:34
 */
public class Test {
    public static void main(String[] args) {
        Mouse mouse = new Mouse("老鼠",56);
        mouse.sleep();
        mouse.introduce();
        dog dog = new dog();
        dog.eat();
        dog.run();
        dog.eatTest();
    }
}
