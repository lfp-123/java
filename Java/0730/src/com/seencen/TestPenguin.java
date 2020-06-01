package com.seencen;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class TestPenguin {
    public static void main(String[] args) {
        Penguin penguin = new Penguin(Penguin.GENDER_MALE,Penguin.GENDER_FEMALE,28);
        System.out.println(penguin.toString());
        System.out.println(penguin.getName());
        System.out.println(penguin.getSex());
        System.out.println(penguin.getHealth());


    }
}
