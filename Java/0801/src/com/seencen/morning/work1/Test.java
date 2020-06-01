package com.seencen.morning.work1;

/**
 * @author asus
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 10:11
 */
public class Test {
    public static void main(String[] args) {
        DOG dog = new DOG();
        dog.setName("小黑");
        Panda panda = new Panda();
        panda.setName("滚滚");

        Print print = new Print();
        print.take(dog);
        print.take(panda);

    }
}
