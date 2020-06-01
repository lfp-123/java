package cn.sc.pool;



/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/18 19:32
 */
public class Person{
    private String name="Person";
    int age=0;
}
class Child extends Person{
    public String grade;
    public static void main(String[] args){
        Person p = new Child();
        System.out.println();
    }
}