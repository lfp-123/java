package com.seecen;

/**
 * @author ${林锋鹏}
 * @Title: Demo1
 * @ProjectName Java
 * @date 2019/8/7 11:45
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(fb(4));
        int num = add(10);
        System.out.println(num);
    }
    public  static int fb(int position){
        if(position==1||position ==2){
            return  1;
        }

        return  fb(position-1)+fb(position-2);
    }
    public static int add(int num){
        if(num ==1){
            return 1;
        }

        return add(num -1)+num;

    }
}
