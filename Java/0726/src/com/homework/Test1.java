package com.homework;

/**
 * @author asus
 * @date 2019/7/28 15:44
 */
public class Test1 {
    public static void main(String[] args) {

          show( new cat());
          show( new Dogs());
        Animalss cat = new cat();
        cat.eat();
        cat a =new cat();
        a.eat();
        a.run();


    }


    public static void show(Animalss a ) {
        a.eat();
        if(a instanceof cat){
           ((cat) a).run();
        }else if(a instanceof Dogs){
            ((Dogs) a).run();
        }
    }
}
    abstract class Animalss{
    abstract void eat();
}

class cat extends  Animalss{
    @Override
    public void eat()
    {
        System.out.println("猫再吃>>");
    }
    public void run()
    {
        System.out.println("猫在跑");
    }

}
class Dogs extends  Animalss{
    @Override
    public void eat()
    {
        System.out.println("狗再吃>>");
    }
    public void run()
    {
        System.out.println("狗在跑");
    }

}