package com.seecen.homework;

/**
 * @author ${林锋鹏}
 * @Title: Bird
 * @ProjectName Java
 * @date 2019/8/12 19:26
 */
public abstract class  Bird extends Animals {

    private  double flyspeed;

    public double getFlyspeed() {
        return flyspeed;
    }

    public void setFlyspeed(double flyspeed) {
        this.flyspeed = flyspeed;
    }


    @Override
    void  run() {

    }
    public double getFlyTime(double distance){
        return  distance/flyspeed;
    }

    @Override
    public  double getRundistance(double distance) {
        return 0;
    }
}
