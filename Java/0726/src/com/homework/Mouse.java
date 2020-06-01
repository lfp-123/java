package com.homework;

/**
 * @author asus
 * @date 2019/7/28 11:30
 */
public class Mouse extends Animals {
    public  Mouse(String name,int id){
        super(name,id);


    }
}


class Animal{
  public   void eat(){
        System.out.println("动物再吃");
    }
}

class dog extends Animal{
 public    void eat(){
        System.out.println("狗在吃");
    }
    void eatTest(){
        super.eat();
        this.eat();

    }
 public    void run(){
        System.out.println("跑步");
    }

}