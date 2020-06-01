import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 19:53
 */
public class HomeWork4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一串字符串：");
        String str1 = scanner.next();
        System.out.print("请输入你要查找的字符：");
        String str2 = scanner.next();
        String newstr = new String();
        newstr =  str1.replaceAll(str2,"");
        System.out.println(str1);
        System.out.println(newstr);
        str1.trim();
        newstr.trim();
        int length = str1.length()-newstr.length();
        System.out.println(str1+"中至少包含"+length+"个"+str2+"字符串。");

    }
}
