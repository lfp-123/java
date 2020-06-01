package cn.sc.morning;

/**
 * @author asus
 * @Title: Test1
 * @ProjectName Java
 * @Description: 单例模式的使用
 * @date 2019/11/139:38
 */
public class  Singleton1{

    /*
    * 恶汉式代码！！！*/
    private static  Singleton1 s1 = new Singleton1();
    private  static Singleton1 s2;
    public Singleton1 getSingleton(){
        return  s1;
    }
    public Singleton1 getSingleton1(){

        if(s1==null){
            return  s2 = new Singleton1();

        }
        return s2;
    }

    private Singleton1(){

    }

    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.s1;
        Singleton1 s2 = Singleton1.s1;
        System.out.println(s1.equals(s2));

        Singleton1 s21 = Singleton1.s2;
        Singleton1 s22 = Singleton1.s2;
        System.out.println(s21.equals(s22));
    }
}
