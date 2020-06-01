package com.work5;

public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Penguin penguin = new Penguin();
        Cat cat = new Cat();
        Panda panda = new Panda();
        Master master = new Master();
        master.cure(dog);
        master.cure(penguin);
        master.cure(cat);
        master.cure(panda);

        System.out.println("dog:" + dog.getHealth());
        System.out.println("penguin:" + penguin.getHealth());
        System.out.println("cat:" + cat.getHealth());
        System.out.println("panda:" + panda.getHealth());

    }
}
