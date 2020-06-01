import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 19:31
 */
public class demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            String id,phone,tel;
            String[] telSub;
            System.out.println("***欢迎进入注册系统***");
            System.out.print("请输入身份证：");
            id = sc.next();
            System.out.print("请输入手机号：");
            phone = sc.next();
            System.out.print("请输入座机号：");
            tel = sc.next();
            if (id.length() != 18 || id.length() != 16){
                System.out.println("身份证号必须是16位或18位！");
                continue;
            }
            if (phone.length() != 11){
                System.out.println("手机号码必须是11位！");
                continue;
            }
            telSub = tel.split("-");
            if (telSub[0].length() != 4 || telSub[1].length() != 7){
                System.out.println("座机区号必须为4位，电话号码必须是7位！");
                continue;
            }
            System.out.println("注册成功！");
            return;
        }

    }
}
