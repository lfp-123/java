package com.afternoon;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class TestB {
    public void say(String... strings){
        System.out.println(strings.length);
        for (String a:strings) {
            System.out.print(a+"\t");
        }
    }
    public static void main(String[] args) {

        Dog dog = new Dog();
        dog.setHealth(20);
        Cat cat = new Cat();
        cat.setHealth(80);
        Master master = new Master();
        master.cure(dog);
        master.cure(cat);
        System.out.println("dog:" + dog.getHealth());

        System.out.println("cat:" + cat.getHealth());
        TestB testB = new TestB();
        testB.say();
        testB.say("hh","hhhh","hhhhhhhhhhhhhhhhhh");


    }
}
