package com.work5;

public class Penguin extends Pet {
    @Override
    public void toHospital(){
        this.setHealth(70);
        System.out.println("企鹅疗养的方法");
    }
}
