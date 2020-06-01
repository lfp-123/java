package com.seecen.homework;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName 里氏代换原则
 * @date 2019/8/12 19:40
 */
public class Test {
    public static void main(String[] args) {
        Bird swallow = new Swallow();
        swallow.setFlyspeed(2.5);
        System.out.println("飞行距离为300公里。燕子时速为2.5公里每小时,"
                +swallow.getFlyTime(300));
        Animals brownBird = new BrownBird();
        brownBird.setRunspeed(20);
        ;
        System.out.println("飞行距离为300公里。几唯鸟时速为40公里每小时,"
                +brownBird.getRundistance(300));
    }
}
