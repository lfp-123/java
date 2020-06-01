package cn.sc.homework;

/**
 * @author ${林锋鹏}
 * @Title: Test2
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 17:43
 */
public class Test2 {
    public static void main(String[] args) {
            String a = "miao-wei-ke-tang";
            char[] c = a.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '-') {
                    c[i] = Character.toUpperCase(c[i + 1]);
                    c[i + 1] = '1';
                }
            }
            String s1 = String.valueOf(c); //ar数组转换为String
            String[] split = s1.split("1");
            for (String s : split) {
                System.out.print(s);
            }
    }
}
