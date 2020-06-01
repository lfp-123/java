package cn.sc.homework;

/**
 * @author ${林锋鹏}
 * @Title: Test5
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 17:51
 */
public class Test5 {
    public static void main(String[] args) {
        String str = "      nihao  ji      ";
        String newStr = Test5.myTrim(str);
        System.out.println(newStr);
    }
    public static String myTrim(String str){
        int begin=0;
        int over =0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(!(' '==(chars[i]))){
                begin =i;
                break;
            }
        }
        for (int i = chars.length-1; i > 0; i--) {
            if(!(' '==(chars[i]))){
                over =i;
                break;
            }
        }
        String substring = str.substring(begin, over);
        return substring;

    }
}
