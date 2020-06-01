package com;

/**
 * @author asus
 * @date 2019/7/26 15:19
 */
public class Visitor {
            String  name ;
            int age ;

            public  void showPrice(){

                if(age<18||age>75){
                    System.out.println(name+"免费游玩！");
                }else {
                    System.out.println(name+"请缴纳50元门票费用！");
                }

            }

}
