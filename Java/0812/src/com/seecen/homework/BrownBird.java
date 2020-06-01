package com.seecen.homework;

/**
 * @author ${林锋鹏}
 * @Title: BrownBird
 * @ProjectName Java
 * @date 2019/8/12 19:31
 */
public class BrownBird extends Animals {

    @Override
    void run(){}

    @Override
    public double getRundistance(double distance) {
        return distance/super.getRunspeed();
    }
}
