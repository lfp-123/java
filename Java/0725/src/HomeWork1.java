import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 18:52
 */
public class HomeWork1 {
    public static void main(String[] args) {

            int index =0;
        Scanner scanner = new Scanner(System.in);
       aa: while (true){
            System.out.print("请输入用户名：");
            String name = scanner.next();
//            while (true) {
                System.out.print("请输入密码：");
                String password = scanner.next();
                System.out.print("请再次输入密码");
                String passwordagain = scanner.next();
                if (passwordagain.equals(password)) {
                    if (name.length() < 3 || password.length() < 6) {
                        System.out.println("用户名的长度不得短于3，密码的长度不得短于6！");
                    } else {
                        System.out.println("注册成功！ 请牢记用户名和密码！");
                        break aa;
                    }
//                } else {
//                    System.out.println("请前后输入的密码保持一致！");
//                }
                 }
        }
    }
}
