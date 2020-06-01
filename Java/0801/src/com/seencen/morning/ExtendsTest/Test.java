package com.seencen.morning.ExtendsTest;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入要选择的车的类型(1.轿车 2.客车)");
        int choose = input.nextInt();
        System.out.print("请输入要租赁的天数:");
        int days = input.nextInt();

        if(choose == 1) {
            System.out.print("请输入车型(1."+Cons.CAR_BKSWGL8+"["+Cons.CAR_BKSWGL8_PRICE+"] 2."+Cons.CAR_BMW550I+"["+Cons.CAR_BMW550I_PRICE+"] 3. "+Cons.CAR_BKLYDD+"["+Cons.CAR_BKLYDD_PRICE+"]):");
            int typeChoose = input.nextInt();
            String type = typeChoose == 1 ? Cons.CAR_BKSWGL8 : typeChoose == 2 ? Cons.CAR_BMW550I : Cons.CAR_BKLYDD;
            Car car = new Car("赣A99999", type);
            System.out.println("共需要花费: " + car.calcPrice(days) + "元!");
        }
        if(choose == 2) {
            System.out.print("请输入要使用的座位数量:");
            int searCount = input.nextInt();
            Bus bus = new Bus("赣A88888", searCount);
            System.out.println("共需要花费: " + bus.calcPrice(days) + "元!");
            Calc calc = new Calc();

        }
    }
}
