package com.seencen.morning.work1;

/**
 * @author asus
 * @Title: Print
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 10:09
 */
public class Print {
    public void take(Animals animals){
        animals.eat();
        if(animals instanceof DOG){
            ( (DOG) animals).flyDisk();
        } if(animals instanceof Panda) {
            ((Panda) animals).run();
        }
    }
    public void takeDoctor(Animals animals){

        if(animals.getHealth()<60){
           animals.toHospital();
        }
    }
}
