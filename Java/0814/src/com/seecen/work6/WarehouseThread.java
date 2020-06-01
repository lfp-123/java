package com.seecen.work6;

import java.io.Writer;

/**
 * @author ${林锋鹏}
 * @Title: MyThread
 * @ProjectName Java
 * @date 2019/8/14 17:14
 */
public class WarehouseThread extends Thread {


  private   Warehouse wareHouse;
   private String username;
   private int type;

    public WarehouseThread(String username, Warehouse wareHouse, int type) {
        this.wareHouse = wareHouse;
        this.username = username;
        this.type = type;
    }

    @Override
    public void run()  {
        try {
            while (true) {
                if (type == 1) {
                    wareHouse.producer(username);
                }
                if (type == 2) {
                    wareHouse.consumption(username);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
