package cn.sc.factory;


import java.util.Scanner;

/**
 * @author asus
 * @Title: BeanFactory
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/1310:15
 */
public class BeanFactory {
    public static Fruit getBean(int num){
        Fruit f= null;
        switch (num){
            case 1 : f= new Apple();break;
            case 2 : f= new Banana();break;

        }
        return f;
    }

}
class TestFactory{
    public static void main(String[] args) {
        System.out.println("**************");
        System.out.println("请输入你要的果汁：");
        System.out.println("1,苹果汁");
        System.out.println("2,香蕉汁");
        System.out.println("**************");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        Fruit bean = BeanFactory.getBean(i);
        bean.product();

    }
}