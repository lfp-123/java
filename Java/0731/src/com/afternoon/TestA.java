package com.afternoon;

import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class TestA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入租车的类型：1,轿车 2,客车");

        int choose = scanner.nextInt();
        System.out.print("请选择你要租的天数：");
        int day = scanner.nextInt();
        if(choose==1){
            System.out.println("请输入你要选择的车型：1"+Cons.MOTO_CAR_GLB+"\t2."
                    +Cons.MOTO_CAR_BMW+"\t3."+Cons.MOTO_CAR_BKLYDD);
            String type = scanner.next();


        }else  if(choose==2){
            BusCar busCar = new BusCar();
        System.out.println("请输入你要选择的座位数1."+Cons.BUS_CAR_JB+"\t2."+Cons.BUS_CAR_JL);
        int choosetype = scanner.nextInt();
        String cartype= choosetype==1?Cons.BUS_CAR_JB:Cons.BUS_CAR_JL;
        int type = choosetype==1?Cons.BUS_CAR_JB_MONEY:Cons.BUS_CAR_JL_MONEY;
        busCar.toString(cartype,type,day);
        System.out.println("你需要支付："+busCar.add(day,type));
    }

    }
}
