package com.seencen.morning.ExtendsTest;

public class Test2 {
    public static void main(String[] args) {
        Car bmw1 = new Car("赣M99999", Cons.CAR_BMW550I);
        Car bmw2 = new Car("赣M88888", Cons.CAR_BMW550I);
        Car bksw = new Car("赣M77777", Cons.CAR_BKSWGL8);
        Bus jl = new Bus("赣M66666", 34);
        BigCar bigCar = new BigCar(45);
        bigCar.setNo("赣M77777");
        double v = Calc.calaTotalorice(5, bmw1, bmw2, bksw, jl,bigCar);
        System.out.println("共需花费:" + v);




    }
}
