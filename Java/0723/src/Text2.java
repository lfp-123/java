import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/23 11:33
 */
public class Text2 {
    public static void main(String[] args) {
        String user = "lily";
        String password  = "111111";
        int i=1;
        while (i<4){
            System.out.print("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String users = scanner.next();
            System.out.print("请输入密码：");
            String passwords = scanner.next();
            if(users.equals(user)&&(password.equals(passwords))){
                System.out.println("欢迎登陆Shopping系统");
                break;
            }else{
                System.out.println("输入错误，你还有"+(3-i)+"次机会\n");
                i++;
                continue;
            }
        }
        if(i==4) {
            System.out.println("对不起，三次均输入错误！");
        }

    }
}
