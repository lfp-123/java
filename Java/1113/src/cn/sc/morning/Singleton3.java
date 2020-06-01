package cn.sc.morning;

/**
 * @author asus
 * @Title: Singleton3
 * @ProjectName Java
 * @Description: 通过枚举实现单例模式
 * @date 2019/11/139:45
 */
public class Singleton3 {
    private  Singleton3(){}
    public static Singleton3 getIntance(){
        return intance.INSTANCE.getIntance();
    }
    private enum intance {
        //枚举
        INSTANCE;
        Singleton3 s3;

        intance() {
            s3 = new Singleton3();
        }

        public Singleton3 getIntance() {
            return s3;
        }
    }

    public static void main(String[] args) {
        Singleton3 i1 = Singleton3.getIntance();
        Singleton3 i2 = Singleton3.getIntance();
        System.out.println(i1.equals(i2));
    }
}
