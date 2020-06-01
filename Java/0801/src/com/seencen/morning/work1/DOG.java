package com.seencen.morning.work1;

/**
 * @author asus
 * @Title: DOG
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 10:03
 */
public class DOG extends Animals {



    @Override
    void eat() {
        int health = 10;
        setHealth(getHealth()+health);
        System.out.println("我是一只狗狗："+getName()+"吃饱了，生命力增加"+health);
    }



    public void flyDisk(){
        System.out.println("狗狗"+getName()+"正在接飞盘！");
    }

    @Override
    void toHospital() {
        this.setHealth(60);
        System.out.println("我得健康值为："+getHealth());
    }
}
