package com.seencen.afternoon.work3;

/**
 * @author asus
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 14:39
 */
public class Test {
    public static void main(String[] args) {
        Usb mouse = new Mouse();
        Usb keyBoard = new KeyBoard();
        print(mouse);
        print(keyBoard);

    }
    public static  void print (Usb usb){
        usb.show();
    }
}
