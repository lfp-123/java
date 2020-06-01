package com.homeworks.homework2;

import com.afternoon.Master;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class Test {
    public static void main(String[] args) {

        Bird bird = new Bird();
        Fish fish = new Fish(50);
        Monkey monkey = new Monkey();
        bird.setColor("黄色");
        monkey.setIq(120);
        Print print = new Print();
        print.introduce(monkey);
        print.introduce(bird);
        print.introduce(fish);

    }

}
