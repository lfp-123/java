import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author asus
 * @date 2019/7/23 10:10
 */
public class Text1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String reg1 = "\\d{4}";
        String reg2 = "(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])";
        String reg3 = "\\d{1,4}";
        int count = 3;
        System.out.println("MyShopping管理系统 > 客户信息管理 > 添加客户信息");
        for (int i = 0; i < count; i++) {
            System.out.print("\n请输入会员号（<4位整数>）:");
            String cardNum = input.next();
            System.out.print("请输入会员生日（月/日<用两位整数表示>）：");
            String birthday = input.next();
            System.out.print("请输入会员积分：");
            String gral = input.next();
//            Pattern pattern = Pattern.compile(reg1);
//            pattern.matcher(cardNum).matches();
            if((!Pattern.matches(reg1, cardNum))&&(!birthday.matches(reg2))&&(!gral.matches(reg3))){
                System.out.println("客户号" + cardNum + "是无效的会员号！"+"会员生日" + birthday +
                        "格式错误！"+"积分" + gral + "必须是正整数,且不超过4位！");
                continue;
            }
            if(!Pattern.matches(reg1, cardNum)) {
                System.out.println("客户号" + cardNum + "是无效的会员号！");
                System.out.println("录入信息失败");
                continue;
            }
            if(!birthday.matches(reg2)) {
                System.out.println("会员生日" + birthday + "格式错误！");
                System.out.println("录入信息失败");
                continue;
            }
            if(!gral.matches(reg3)) {
                System.out.println("积分" + gral + "必须是正整数,且不超过4位！");
                System.out.println("录入信息失败");
                continue;
            }
            System.out.println("您录入的会员信息是：");
            System.out.println(cardNum + "\t" + birthday + "\t" + Integer.parseInt(gral));
        }


    }
}

