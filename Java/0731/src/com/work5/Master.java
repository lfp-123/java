package com.work5;

/**
 * 1. 父类的引用指向子类的对象,即父类接收子类对象
 * 2. 重写和重载
 * 3. 可变参数
 */
public class Master {
    public void cure(Pet pet) {
        System.out.println("");
        if(pet.getHealth() < 50) {
            pet.toHospital();
        }
    }
}
