package com.work1;

public class Dog extends Pet {
    public Dog(){}
    private String strain;
    public String getStrain() {
        return strain;
    }
    public void setStrain(String strain) {
        this.strain = strain;
    }
    @Override
    public void introduce() {
        System.out.println("狗狗品种为:" + getStrain());
    }
}
