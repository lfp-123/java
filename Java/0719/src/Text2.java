import java.util.Scanner;
/**
 * @author asus
 * @date 2019/7/19 10:29
 */
public class Text2{
    public static void main(String[] args) {
        System.out.println("请输入消费金额：");
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        System.out.println("是否选择参加换购活动？");
        System.out.println("1：满50元加2元换购百事可乐饮料");
        System.out.println("2：满100元加10元换购面粉");
        System.out.println("3：满100元加3元换500ml可乐");
        System.out.println("4：满200元加10元换购炒菜锅");
        System.out.println("5：满200元加20元换购欧莱雅爽肤水");
        System.out.println("0:不换购");
        System.out.println("请输入活动对应的序列");
        int num = scanner.nextInt();
        String data = "条件不允许";
        int a =0 ,b=2;

        switch (a%b) {
                case 1:
                    if(money>50){
                       money=money+2;
                       data = "成功换购可乐";
                    }
                    break;
                case 2:
                    if(money>100){
                        money=money+2;
                        data = "成功换购米粉";
                    }
                    break;
                case 3:
                    if(money>100){
                        money=money+10;
                        data = "成功换购可乐";
                    }
                    break;
                case 4:
                    if(money>200){
                        money=money+20;
                        data = "成功换购炒菜锅";
                    }
                    break;
                case 5:
                    if(money>200){
                        money=money+2;
                        data = "成功换购大米";
                    }
                    break;
                case 0:
                        data="不换购";
                    break;
                default:
                    System.out.println("输入错误！");
                    return;
            }
        System.out.println("本次消费总金额:" + money);
        System.out.println(data);
    }
}
