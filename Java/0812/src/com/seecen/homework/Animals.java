package com.seecen.homework;

/**
 * @author ${林锋鹏}
 * @Title: Animals
 * @ProjectName 里氏代换原则
 * @date 2019/8/12 19:22
 */
public abstract class   Animals {
    private double runspeed;
    public double getRunspeed() {
        return runspeed;
    }

    public void setRunspeed(double runspeed) {
        this.runspeed = runspeed;
    }


    abstract  void run();

    public abstract double getRundistance(double distance);


}
