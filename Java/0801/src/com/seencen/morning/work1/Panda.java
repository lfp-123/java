package com.seencen.morning.work1;

/**
 * @author asus
 * @Title: Panda
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 10:07
 */
public class Panda extends Animals {
    @Override
    void eat() {
        int health = 10;
        setHealth(getHealth()+health);

        System.out.println("我是一只熊猫："+getName()+"吃饱了，生命力增加"+health);
    }
    public  void run(){
        System.out.println(getName()+"在疯狂得滚动");
    }

    @Override
    void toHospital() {
        this.setHealth(80);
        System.out.println("我得健康值为："+getHealth());
    }
}
