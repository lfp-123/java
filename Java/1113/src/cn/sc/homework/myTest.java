package cn.sc.homework;

/**
 * @author ${林锋鹏}
 * @Title: myTest
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 19:13
 */
public class myTest {
    public static void main(String[] args) {
       String ss="    abc   ";
        char[] chars = ss.toCharArray();
        for (char s :
                chars) {
            System.out.print(s+",");
        }
    }
}
