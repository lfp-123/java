package cn.sc.morning;

/**
 * @author asus
 * @Title: Test2
 * @ProjectName Java
 * @Description:
 * @date 2019/11/139:43
 */
public class Singleton2 {
    static  Singleton2 s1;
    static {
        s1 =new Singleton2();
    }
    private Singleton2(){

    }

    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.s1;
        Singleton2 s2 = Singleton2.s1;
        System.out.println(s1.equals(s2));
    }

}
