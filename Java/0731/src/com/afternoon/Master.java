package com.afternoon;



/**
 * Created by Enzo Cotter on 2019/7/31.
 */
public class Master {
    public  void cure(Animals animals){
        if(animals.getHealth()<50) {
            animals.toHospital();
        }
    }

}
